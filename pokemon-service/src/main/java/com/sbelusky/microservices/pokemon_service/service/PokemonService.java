package com.sbelusky.microservices.pokemon_service.service;

import com.sbelusky.microservices.pokemon_service.client.UserClient;
import com.sbelusky.microservices.pokemon_service.dto.*;
import com.sbelusky.microservices.pokemon_service.event.PokemonCreatedEvent;
import com.sbelusky.microservices.pokemon_service.exception.AbilityNotFoundException;
import com.sbelusky.microservices.pokemon_service.exception.PokemonNotFoundException;
import com.sbelusky.microservices.pokemon_service.exception.SpeciesNotFoundException;
import com.sbelusky.microservices.pokemon_service.exception.UserNotFoundException;
import com.sbelusky.microservices.pokemon_service.model.Ability;
import com.sbelusky.microservices.pokemon_service.model.Pokemon;
import com.sbelusky.microservices.pokemon_service.model.Species;
import com.sbelusky.microservices.pokemon_service.repository.AbilityRepository;
import com.sbelusky.microservices.pokemon_service.repository.PokemonRepository;
import com.sbelusky.microservices.pokemon_service.repository.SpeciesRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PokemonService {
    private static final Logger log = LoggerFactory.getLogger(PokemonService.class);
    private final PokemonRepository pokemonRepository;
    private final SpeciesRepository speciesRepository;
    private final AbilityRepository abilityRepository;
    private final UserClient userClient;
    private final KafkaTemplate<String, PokemonCreatedEvent> kafkaTemplate;

    public PokemonService(PokemonRepository pokemonRepository, SpeciesRepository speciesRepository, AbilityRepository abilityRepository, UserClient userClient, KafkaTemplate<String, PokemonCreatedEvent> kafkaTemplate) {
        this.pokemonRepository = pokemonRepository;
        this.speciesRepository = speciesRepository;
        this.abilityRepository = abilityRepository;
        this.userClient = userClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Creates new Pokemon and saves it to the database
     *
     * @param pokemonRequest contains information about the Pokemon to be created
     * @return PokemonResponse containing information about the created Pokemon
     */
    public PokemonResponse createPokemon(@Valid PokemonRequest pokemonRequest) throws SpeciesNotFoundException, AbilityNotFoundException, UserNotFoundException {
        // check if species exists
        Species species = speciesRepository
                .findById(pokemonRequest.speciesId())
                .orElseThrow(() -> new SpeciesNotFoundException("Species not found with id: " + pokemonRequest.speciesId()));

        List<Long> abilityIds = pokemonRequest.abilities().stream()
                .map(AbilityRequest::id)
                .collect(Collectors.toList());

        Set<Ability> abilities = abilityRepository.findAllById(abilityIds).stream()
                .collect(Collectors.toSet());

        if (abilities.size() != pokemonRequest.abilities().size()) {
            throw new AbilityNotFoundException("Abilities not found with ids: " + pokemonRequest.abilities().stream().map(AbilityRequest::id).collect(Collectors.toSet()));
        }

        // create new Pokemon
        Pokemon pokemon = new Pokemon(pokemonRequest.name(), pokemonRequest.birthDate(), species, abilities, pokemonRequest.ownerId());

        // get user by id from user service
        UserResponse userResponse = userClient.getUserById(pokemonRequest.ownerId());
        if (userResponse == null || userResponse.email() == null) {
            throw new UserNotFoundException("User not found with id: " + pokemonRequest.ownerId());
        }

        // save Pokemon to the database
        pokemonRepository.save(pokemon);

        // send message to Kafka Topic
        if(pokemon.getName() != null && userResponse.email() != null){
            PokemonCreatedEvent pokemonCreatedEvent = new PokemonCreatedEvent();

            pokemonCreatedEvent.setName(pokemon.getName());
            pokemonCreatedEvent.setOwnerEmail(userResponse.email());

            if (pokemonCreatedEvent == null) {
                throw new IllegalArgumentException("pokemonCreatedEvent is null!");
            }
            else{
                log.info("Start - sending message to Kafka Topic pokemon-created: {}", pokemonCreatedEvent);
                try {
                    kafkaTemplate.send("pokemon-created", pokemonCreatedEvent);
                } catch (Exception e) {
                    System.out.println("Error sending message to Kafka Topic pokemon-created: " + e.getMessage());
                }
                log.info("End - sending message to Kafka Topic pokemon-created: {}", pokemonCreatedEvent);
            }
        }


        // return PokemonResponse containing information about created Pokemon
        return new PokemonResponse(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getBirthDate(),
                getSpeciesResponse(pokemon),
                getAbilityResponses(pokemon),
                pokemon.getCreatedAt(),
                pokemon.getUpdatedAt()
        );
    }

    /**
     * Retrieves Pokemon from the database
     *
     * @param id id of the Pokemon to be retrieved
     * @return PokemonResponse containing information about the retrieved Pokemon
     */
    public PokemonResponse getPokemonById(Long id) throws PokemonNotFoundException {
        // check if Pokemon exists
        Pokemon pokemon = pokemonRepository
                .findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon not found with id: " + id));

        // convert Species to SpeciesResponse
        SpeciesResponse speciesResponse = getSpeciesResponse(pokemon);

        // convert Set of Abilities to Set of AbilityResponses
        Set<AbilityResponse> abilityResponses = getAbilityResponses(pokemon);

        // return PokemonResponse containing information about retrieved Pokemon
        return new PokemonResponse(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getBirthDate(),
                speciesResponse,
                abilityResponses,
                pokemon.getCreatedAt(),
                pokemon.getUpdatedAt());

    }

    public Set<PokemonResponse> getPokemonsByOwnerId(Long ownerId) throws UserNotFoundException {

        UserResponse userResponse = userClient.getUserById(ownerId);
        if (userResponse == null || userResponse.email() == null) {
            throw new UserNotFoundException("User not found with id: " + ownerId);
        }

        Example<Pokemon> example = Example.of(new Pokemon());
        example.getProbe().setOwnerId(ownerId);

        Set<PokemonResponse> pokemonResponses = pokemonRepository.findAll(example)
                .stream()
                .map(pokemon -> new PokemonResponse(
                        pokemon.getId(),
                        pokemon.getName(),
                        pokemon.getBirthDate(),
                        getSpeciesResponse(pokemon),
                        getAbilityResponses(pokemon),
                        pokemon.getCreatedAt(),
                        pokemon.getUpdatedAt()))
                .collect(Collectors.toSet());

        return pokemonResponses;
    }

    /**
     * Converts Set of Abilities to Set of AbilityResponses
     *
     * @param pokemon contains set of Abilities
     * @return Set of AbilityResponses
     */
    private static Set<AbilityResponse> getAbilityResponses(Pokemon pokemon) {
        // convert Set of Abilities to Set of AbilityResponses
        Set<Ability> abilities = pokemon.getAbilities();

        if(abilities == null) {
            return null;
        }
        Set<AbilityResponse> abilityResponses = abilities.stream()
                .map(ability -> new AbilityResponse(ability.getName()))
                .collect(Collectors.toSet());

        return abilityResponses;
    }

    /**
     * Converts Species to SpeciesResponse
     *
     * @param pokemon contains Species
     * @return SpeciesResponse
     */
    private static SpeciesResponse getSpeciesResponse(Pokemon pokemon) {
        // convert Species to SpeciesResponse
        Species species = pokemon.getSpecies();

        SpeciesResponse speciesResponse = new SpeciesResponse(
                species.getId(),
                species.getName(),
                species.getType(),
                species.getWeight(),
                species.getPhotoImage(),
                species.getBaseHappiness(),
                species.getCaptureRate(),
                species.getColor(),
                species.getHabitat(),
                species.getMythical(),
                species.getShape(),
                species.getGrowthRate()
        );
        return speciesResponse;
    }
}
