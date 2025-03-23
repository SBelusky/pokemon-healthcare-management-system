package com.sbelusky.microservices.appointment_service.client;

import com.sbelusky.microservices.appointment_service.dto.UserResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

/**
 * REST client for communication with the user service.
 */
public interface UserClient {
    /**
     * Retrieves a user by its ID.
     *
     * @param id the ID of the user to retrieve
     * @return the response containing the user details
     */
    @Operation(
        summary = "Get user by ID",
        description = "Retrieves a user by its ID."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved user")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetExchange("/api/users/{id}")
    @CircuitBreaker(name="user", fallbackMethod = "fallbackMethod")
    @Retry(name="user")
    UserResponse getUserById(@Parameter(description = "ID of the user to be retrieved") @PathVariable("id") Long id);

    /**
     * Fallback method for getUserById when an error occurs.
     *
     * @param id the ID of the user
     * @param throwable the error that occurred
     * @return a default UserResponse indicating the failure
     */
    default UserResponse fallbackMethod(Long id, Throwable throwable){
        Logger log = LoggerFactory.getLogger(UserClient.class);

        String errorText = "Cannot get user by ID: " + id + ", " + throwable.getMessage();
        log.info(errorText);

        return null;
    }
}
