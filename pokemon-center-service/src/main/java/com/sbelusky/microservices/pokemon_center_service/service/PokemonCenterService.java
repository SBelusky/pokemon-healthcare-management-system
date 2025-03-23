package com.sbelusky.microservices.pokemon_center_service.service;

import com.sbelusky.microservices.pokemon_center_service.dto.PokemonCenterRequest;
import com.sbelusky.microservices.pokemon_center_service.dto.PokemonCenterResponse;
import com.sbelusky.microservices.pokemon_center_service.exception.PokemonCenterNotFoundException;
import com.sbelusky.microservices.pokemon_center_service.model.PokemonCenter;
import com.sbelusky.microservices.pokemon_center_service.repository.PokemonCenterRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for managing Pokemon Center data.
 * It provides methods for creating, retrieving, and listing Pokemon Centers.
 */
@Service
public class PokemonCenterService {
    private static final Logger log = LoggerFactory.getLogger(PokemonCenterService.class);

    private final PokemonCenterRepository pokemonCenterRepository;

    /**
     * Constructor for PokemonCenterService.
     * @param pokemonCenterRepository the PokemonCenterRepository used to interact with the MongoDB collection.
     */
    public PokemonCenterService(PokemonCenterRepository pokemonCenterRepository) {
        this.pokemonCenterRepository = pokemonCenterRepository;
    }

    /**
     * Creates a new Pokemon Center and saves it to the database.
     * @param pokemonCenterRequest the PokemonCenterRequest object containing the Pokemon Center data.
     * @return the PokemonCenterResponse object containing the created Pokemon Center.
     */
    public PokemonCenterResponse createPokemonCenter(@Valid PokemonCenterRequest pokemonCenterRequest) {

        PokemonCenter pokemonCenter = new PokemonCenter(
                pokemonCenterRequest.name(),
                pokemonCenterRequest.city(),
                pokemonCenterRequest.street(),
                pokemonCenterRequest.country(),
                pokemonCenterRequest.phoneNumber(),
                pokemonCenterRequest.email(),
                pokemonCenterRequest.specialization()
        );

        pokemonCenterRepository.save(pokemonCenter);

        PokemonCenterResponse pokemonCenterResponse = getPokemonCenterResponse(pokemonCenter);

        return pokemonCenterResponse;
    }

    /**
     * Retrieves a Pokemon Center by its ID.
     * @param id the ID of the Pokemon Center to be retrieved.
     * @return the PokemonCenterResponse object containing the Pokemon Center.
     * @throws PokemonCenterNotFoundException if the Pokemon Center is not found.
     */
    public PokemonCenterResponse getPokemonCenterById(Long id) throws PokemonCenterNotFoundException {
        PokemonCenter pokemonCenter = pokemonCenterRepository.findById(id)
                .orElseThrow(() -> new PokemonCenterNotFoundException("PokemonCenter not found with id: " + id));

        return getPokemonCenterResponse(pokemonCenter);
    }

    /**
     * Retrieves all Pokemon Centers.
     * @return a List of PokemonCenterResponse objects containing all Pokemon Centers.
     */
    public List<PokemonCenterResponse> getAllPokemonCenters() {

        return pokemonCenterRepository.findAll()
                .stream()
                .map(PokemonCenterService::getPokemonCenterResponse)
                .toList();
    }

    /**
     * Maps a PokemonCenter object to a PokemonCenterResponse object.
     * @param pokemonCenter the PokemonCenter object to be mapped.
     * @return the PokemonCenterResponse object containing the Pokemon Center data.
     */
    private static PokemonCenterResponse getPokemonCenterResponse(PokemonCenter pokemonCenter) {
        PokemonCenterResponse pokemonCenterResponse = new PokemonCenterResponse(
                pokemonCenter.getName(),
                pokemonCenter.getCity(),
                pokemonCenter.getStreet(),
                pokemonCenter.getCountry(),
                pokemonCenter.getPhoneNumber(),
                pokemonCenter.getEmail(),
                pokemonCenter.getSpecialization(),
                pokemonCenter.getCreatedAt(),
                pokemonCenter.getUpdatedAt()
        );
        return pokemonCenterResponse;
    }
}
