package com.sbelusky.microservices.pokemon_service.controller;

import com.sbelusky.microservices.pokemon_service.dto.SpeciesResponse;
import com.sbelusky.microservices.pokemon_service.service.SpeciesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {
    private final SpeciesService speciesService;

    /**
     * Constructs a SpeciesController with the provided SpeciesService.
     *
     * @param speciesService the service for managing species operations
     */
    public SpeciesController(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    /**
     * Retrieves all species.
     *
     * @return a set of SpeciesResponse containing information about all species
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all species", description = "Retrieves a list of all species")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all species")
    public Set<SpeciesResponse> getAllSpecies() {
        return speciesService.getAllSpecies();
    }
}