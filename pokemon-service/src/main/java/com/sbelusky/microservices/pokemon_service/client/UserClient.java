package com.sbelusky.microservices.pokemon_service.client;

import com.sbelusky.microservices.pokemon_service.dto.UserResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

/**
 * Interface which represents a client for User service.
 * This interface is used by Pokemon service to communicate with User service.
 */
public interface UserClient {

    /**
     * Retrieves user by its id from User service.
     * @param id id of the user to be retrieved
     * @return UserResponse containing information about the retrieved user
     */
    @GetExchange("/api/users/{id}")
    @CircuitBreaker(name="user", fallbackMethod = "fallbackMethod")
    @Retry(name="user")
    UserResponse getUserById(@PathVariable("id") Long id);

    /**
     * Fallback method which is called when circuit breaker or retry mechanism is triggered.
     * In this case, it logs an error message and returns null.
     * @param id id of the user which was tried to be retrieved
     * @param throwable Throwable which caused the circuit breaker or retry to be triggered
     * @return null
     */
    default UserResponse fallbackMethod(Long id, Throwable throwable){
        Logger log = LoggerFactory.getLogger(UserClient.class);

        String errorText = "Cannot get user by ID: " + id + ", " + throwable.getMessage();
        log.info(errorText);

        return null;
    }
}



