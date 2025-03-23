package com.sbelusky.microservices.pokemon_service.controller;

import com.sbelusky.microservices.pokemon_service.dto.AbilityResponse;
import com.sbelusky.microservices.pokemon_service.service.AbilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * REST controller for abilities.
 */
@RestController
@RequestMapping("api/abilities")
public class AbilityController {
    private AbilityService abilityService;

    /**
     * Constructor.
     * @param abilityService ability service
     */
    public AbilityController(AbilityService abilityService) {
        this.abilityService = abilityService;
    }

    /**
     * Get all abilities.
     * @return set of abilities
     */
    @GetMapping
    @Operation(summary = "Get all abilities", description = "Returns all abilities")
    @ApiResponse(responseCode = "200", description = "OK")
    @ResponseStatus(HttpStatus.OK)
    public Set<AbilityResponse> getAllAbilities() {
        return abilityService.getAllAbilities();
    }
}
