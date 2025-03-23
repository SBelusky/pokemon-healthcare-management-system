package com.sbelusky.microservices.treatment_service.client;

import com.sbelusky.microservices.treatment_service.dto.UserResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface UserClient {
    /**
     * Retrieves a user by their ID from the User service.
     * Utilizes circuit breaker and retry mechanisms for resilience.
     *
     * @param id the ID of the user to be retrieved
     * @return UserResponse containing user details if successful
     */
    @GetExchange("/api/users/{id}")
    @CircuitBreaker(name = "user", fallbackMethod = "fallbackMethod")
    @Retry(name = "user")
    UserResponse getUserById(@PathVariable("id") Long id);

    /**
     * Fallback method called when an error occurs during getUserById.
     * Logs the error and returns a default response.
     *
     * @param id the ID of the user that failed to be retrieved
     * @param throwable the error that caused the failure
     * @return a default UserResponse indicating the failure
     */
    default UserResponse fallbackMethod(Long id, Throwable throwable) {
        Logger log = LoggerFactory.getLogger(UserClient.class);

        String errorText = "Cannot get user by ID: " + id + ", " + throwable.getMessage();
        log.info(errorText);

        return null;
    }
}