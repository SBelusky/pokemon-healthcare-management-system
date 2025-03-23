package com.sbelusky.microservices.treatment_service.controller;

import com.sbelusky.microservices.treatment_service.dto.TreatmentHistoryResponse;
import com.sbelusky.microservices.treatment_service.exception.PokemonNotFoundException;
import com.sbelusky.microservices.treatment_service.exception.TreatmentHistoryNotFound;
import com.sbelusky.microservices.treatment_service.exception.UserHasNoPokemonException;
import com.sbelusky.microservices.treatment_service.exception.UserNotFoundException;
import com.sbelusky.microservices.treatment_service.service.TreatmentHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controller for working with treatment history data.
 */
@RestController
@RequestMapping("/api/treatment-history")
public class TreatmentHistoryController {

    private final TreatmentHistoryService treatmentHistoryService;

    /**
     * Constructor for TreatmentHistoryController.
     *
     * @param treatmentHistoryService the treatment history service
     */
    public TreatmentHistoryController(TreatmentHistoryService treatmentHistoryService) {
        this.treatmentHistoryService = treatmentHistoryService;
    }

    /**
     * Gets treatment history for a pokemon.
     *
     * @param pokemonId the id of the pokemon
     * @return the treatment history for the pokemon
     * @throws PokemonNotFoundException   if the pokemon is not found
     * @throws TreatmentHistoryNotFound if the treatment history is not found
     */
    @GetMapping("/pokemon/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get treatment history by pokemon id", description = "Gets treatment history for a pokemon")
    @ApiResponse(responseCode = "200", description = "Treatment history for pokemon")
    @ApiResponse(responseCode = "404", description = "Pokemon not found or treatment history not found")
    public TreatmentHistoryResponse getTreatmentHistoryByPokemonId(
            @Parameter(name = "id", description = "The id of the pokemon") @PathVariable("id") Long pokemonId) throws PokemonNotFoundException, TreatmentHistoryNotFound {
        return treatmentHistoryService.getTreatmentHistoryByPokemonId(pokemonId);
    }

    /**
     * Gets treatment history for a user.
     *
     * @param ownerId the id of the user
     * @return the treatment history for the user
     * @throws UserNotFoundException     if the user is not found
     * @throws UserHasNoPokemonException if the user has no pokemons
     */
    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get treatment history by user id", description = "Gets treatment history for a user")
    @ApiResponse(responseCode = "200", description = "Treatment history for user")
    @ApiResponse(responseCode = "404", description = "User not found or user has no pokemons")
    public Set<TreatmentHistoryResponse> getTreatmentHistoryByOwnerId(
            @Parameter(name = "id", description = "The id of the user") @PathVariable("id") Long ownerId) throws UserNotFoundException, UserHasNoPokemonException {
        return treatmentHistoryService.getTreatmentHistoryByOwnerId(ownerId);
    }
}
