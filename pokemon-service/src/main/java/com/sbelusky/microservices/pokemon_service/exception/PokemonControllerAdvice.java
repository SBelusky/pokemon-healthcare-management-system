package com.sbelusky.microservices.pokemon_service.exception;

import com.sbelusky.microservices.pokemon_service.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;
import java.util.stream.Collectors;

@Hidden
@RestControllerAdvice
public class PokemonControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(PokemonControllerAdvice.class);

    /**
     * Handles validation exceptions for requests with invalid method arguments.
     *
     * @param ex the exception containing validation errors
     * @param request the HTTP request that caused the exception
     * @return a response entity with an error response and HTTP status BAD_REQUEST
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage(),
                        (existing, _) -> existing));

        log.error("Validation exception for request [{} {}]: {}\nErrors: {}", request.getMethod(), request.getRequestURI(), ex.getMessage(), errors, ex);

        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions when an ability is not found.
     *
     * @param ex the exception containing the error message
     * @param request the HTTP request that caused the exception
     * @return a response entity with the error message and HTTP status NOT_FOUND
     */
    @ExceptionHandler(AbilityNotFoundException.class)
    public ResponseEntity<String> handleAbilityNotFoundException(AbilityNotFoundException ex, HttpServletRequest request) {
        logRequestException(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles exceptions when a Pokemon is not found.
     *
     * @param ex the exception containing the error message
     * @param request the HTTP request that caused the exception
     * @return a response entity with the error message and HTTP status NOT_FOUND
     */
    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<String> handlePokemonNotFoundException(PokemonNotFoundException ex, HttpServletRequest request) {
        logRequestException(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles exceptions when a species is not found.
     *
     * @param ex the exception containing the error message
     * @param request the HTTP request that caused the exception
     * @return a response entity with the error message and HTTP status NOT_FOUND
     */
    @ExceptionHandler(SpeciesNotFoundException.class)
    public ResponseEntity<String> handleSpeciesNotFoundException(SpeciesNotFoundException ex, HttpServletRequest request) {
        logRequestException(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles exceptions when a user is not found.
     *
     * @param ex the exception containing the error message
     * @param request the HTTP request that caused the exception
     * @return a response entity with the error message and HTTP status NOT_FOUND
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        logRequestException(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Logs exceptions for requests, including the request method, URI, and exception message.
     *
     * @param ex the exception to log
     * @param request the HTTP request that caused the exception
     */
    private static void logRequestException(Exception ex, HttpServletRequest request) {
        log.error("Exception for request [{} {}]: {}\n", request.getMethod(), request.getRequestURI(), ex.getMessage(), ex);
    }
}