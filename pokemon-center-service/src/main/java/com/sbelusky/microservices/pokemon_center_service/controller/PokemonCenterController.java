package com.sbelusky.microservices.pokemon_center_service.controller;

import com.sbelusky.microservices.pokemon_center_service.dto.PokemonCenterRequest;
import com.sbelusky.microservices.pokemon_center_service.dto.PokemonCenterResponse;
import com.sbelusky.microservices.pokemon_center_service.exception.PokemonCenterNotFoundException;
import com.sbelusky.microservices.pokemon_center_service.service.PokemonCenterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for managing Pokemon Centers
 */
@RestController
@RequestMapping("/api/pokemon-centers")
public class PokemonCenterController {
    private final PokemonCenterService pokemonCenterService;

    public PokemonCenterController(PokemonCenterService pokemonCenterService) {
        this.pokemonCenterService = pokemonCenterService;
    }

    /**
     * Creates a new Pokemon Center
     * @param pokemonCenterRequest JSON object containing the Pokemon Center data
     * @return JSON object containing the created Pokemon Center
     * @throws PokemonCenterNotFoundException if the Pokemon Center already exists
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new Pokemon Center", description = "Creates a new Pokemon Center based on the provided data")
    @ApiResponse(responseCode = "201", description = "Pokemon Center created")
    @ApiResponse(responseCode = "409", description = "Pokemon Center already exists")
    public PokemonCenterResponse createPokemonCenter(@Valid @RequestBody PokemonCenterRequest pokemonCenterRequest){
        return pokemonCenterService.createPokemonCenter(pokemonCenterRequest);
    }

    /**
     * Retrieves all Pokemon Centers
     * @return List of JSON objects containing all Pokemon Centers
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all Pokemon Centers", description = "Retrieves all Pokemon Centers")
    @ApiResponse(responseCode = "200", description = "List of all Pokemon Centers")
    public List<PokemonCenterResponse> getAllPokemonCenters(){
        return pokemonCenterService.getAllPokemonCenters();
    }

    /**
     * Retrieves a Pokemon Center by ID
     * @param id ID of the Pokemon Center to be retrieved
     * @return JSON object containing the Pokemon Center
     * @throws PokemonCenterNotFoundException if the Pokemon Center doesn't exist
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a Pokemon Center by ID", description = "Retrieves a Pokemon Center by ID")
    @ApiResponse(responseCode = "200", description = "Pokemon Center retrieved")
    @ApiResponse(responseCode = "404", description = "Pokemon Center not found")
    public PokemonCenterResponse getPokemonCenterById(@PathVariable Long id) throws PokemonCenterNotFoundException {
        return pokemonCenterService.getPokemonCenterById(id);
    }
}
