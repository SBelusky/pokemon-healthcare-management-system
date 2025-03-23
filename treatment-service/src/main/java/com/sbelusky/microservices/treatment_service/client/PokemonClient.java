package com.sbelusky.microservices.treatment_service.client;

import com.sbelusky.microservices.treatment_service.dto.PokemonResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Collections;
import java.util.Set;

/**
 * Interface for calling Pokemon microservice. It contains two methods: getting a Pokemon by its ID and getting Pokemons by owner ID.
 * Both methods are annotated with CircuitBreaker and Retry, which means that they are protected against service unavailability and
 * can be retried multiple times if the request fails.
 */
public interface PokemonClient {
    /**
     * Retrieves a Pokemon by its ID. If the request fails, it is retried multiple times and then a fallback method is called,
     * which logs the error and returns null.
     * @param id the ID of the Pokemon to be retrieved
     * @return the response containing the Pokemon details
     */
    @GetExchange("/api/pokemons/{id}")
    @CircuitBreaker(name="pokemon", fallbackMethod = "fallbackGetPokemonById")
    @Retry(name="pokemon")
    PokemonResponse getPokemonById(@PathVariable("id") Long id);

    /**
     * Retrieves Pokemons by owner ID. If the request fails, it is retried multiple times and then a fallback method is called,
     * which logs the error and returns an empty set.
     * @param ownerId the ID of the owner of the Pokemons to be retrieved
     * @return the response containing the Pokemons details
     */
    @GetExchange("/api/pokemons")
    @CircuitBreaker(name="pokemon", fallbackMethod = "fallbackGetPokemonsByOwnerId")
    @Retry(name="pokemon")
    Set<PokemonResponse> getPokemonsByOwnerId(@RequestParam("ownerId") Long ownerId);

    /**
     * Fallback method for getPokemonById when an error occurs. It logs the error and returns null.
     * @param id the ID of the Pokemon which was tried to be retrieved
     * @param throwable the error that occurred
     * @return null
     */
    default PokemonResponse fallbackGetPokemonById(Long id, Throwable throwable){
        Logger log = LoggerFactory.getLogger(PokemonResponse.class);

        String errorText = "Cannot get pokemon by ID: " + id + ", " + throwable.getMessage();
        log.info(errorText);

        return null;
    }

    /**
     * Fallback method for getPokemonsByOwnerId when an error occurs. It logs the error and returns an empty set.
     * @param id the ID of the owner which was tried to be retrieved
     * @param throwable the error that occurred
     * @return an empty set
     */
    default Set<PokemonResponse> fallbackGetPokemonsByOwnerId(Long id, Throwable throwable){
        Logger log = LoggerFactory.getLogger(PokemonResponse.class);

        String errorText = "Cannot get pokemon by owner ID: " + id + ", " + throwable.getMessage();
        log.info(errorText);

        return Collections.emptySet();
    }
}
