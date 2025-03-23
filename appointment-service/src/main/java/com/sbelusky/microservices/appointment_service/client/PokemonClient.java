package com.sbelusky.microservices.appointment_service.client;

import com.sbelusky.microservices.appointment_service.dto.PokemonResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface PokemonClient {

    /**
     * Retrieves a Pokemon by its ID.
     *
     * @param id the ID of the Pokemon to retrieve
     * @return the response containing the Pokemon details
     */
    @Operation(summary = "Get Pokemon by ID", description = "Retrieves a Pokemon by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved Pokemon"),
        @ApiResponse(responseCode = "404", description = "Pokemon not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetExchange("/api/pokemons/{id}")
    @CircuitBreaker(name="pokemon", fallbackMethod = "fallbackMethod")
    @Retry(name="pokemon")
    PokemonResponse getPokemonById(@Parameter(description = "ID of the Pokemon to be retrieved") @PathVariable("id") Long id);

    /**
     * Fallback method for getPokemonById when an error occurs.
     *
     * @param id the ID of the Pokemon
     * @param throwable the error that occurred
     * @return a default PokemonResponse indicating the failure
     */
    default PokemonResponse fallbackMethod(Long id, Throwable throwable){
        Logger log = LoggerFactory.getLogger(PokemonResponse.class);

        String errorText = "Cannot get pokemon by ID: " + id + ", " + throwable.getMessage();
        log.info(errorText);

        return new PokemonResponse(null, null, null, null, null, null);
    }
}
