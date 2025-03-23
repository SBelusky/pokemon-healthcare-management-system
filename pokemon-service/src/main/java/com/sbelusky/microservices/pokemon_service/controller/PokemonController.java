package com.sbelusky.microservices.pokemon_service.controller;

import com.sbelusky.microservices.pokemon_service.dto.PokemonRequest;
import com.sbelusky.microservices.pokemon_service.dto.PokemonResponse;
import com.sbelusky.microservices.pokemon_service.exception.AbilityNotFoundException;
import com.sbelusky.microservices.pokemon_service.exception.PokemonNotFoundException;
import com.sbelusky.microservices.pokemon_service.exception.SpeciesNotFoundException;
import com.sbelusky.microservices.pokemon_service.exception.UserNotFoundException;
import com.sbelusky.microservices.pokemon_service.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {
    private PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    /**
     * Creates a new Pokemon.
     *
     * @param pokemonRequest contains information about the Pokemon to be created
     * @return PokemonResponse containing information about the created Pokemon
     */
    @Operation(summary = "Create a new Pokemon")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Pokemon successfully created")
    public PokemonResponse createPokemon(@Valid @RequestBody PokemonRequest pokemonRequest) throws UserNotFoundException, AbilityNotFoundException, SpeciesNotFoundException {
        // Delegate creation to the service layer
        return pokemonService.createPokemon(pokemonRequest);
    }

    /**
     * Retrieves a Pokemon by its ID.
     *
     * @param id the ID of the Pokemon to be retrieved
     * @return PokemonResponse containing information about the retrieved Pokemon
     */
    @Operation(summary = "Retrieve a Pokemon by its ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Pokemon successfully retrieved")
    public PokemonResponse getPokemonById(@Parameter(description = "The ID of the Pokemon to be retrieved") @PathVariable("id") Long id) throws PokemonNotFoundException {
        // Delegate retrieval to the service layer
        return pokemonService.getPokemonById(id);
    }

    @Operation(summary = "Retrieve Pokemons by owner ID")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Pokemons successfully retrieved")
    public Set<PokemonResponse> getPokemonsByOwnerId(@Parameter(description = "The ID of the owner of the Pokemons to be retrieved") @RequestParam("ownerId") Long ownerId) throws UserNotFoundException {
        // Delegate retrieval to the service layer
        return pokemonService.getPokemonsByOwnerId(ownerId);
    }
}
