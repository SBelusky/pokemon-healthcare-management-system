package com.sbelusky.microservices.pokemon_center_service.exception;

import com.sbelusky.microservices.pokemon_center_service.dto.ErrorResponse;
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
public class PokemonCenterControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(PokemonCenterControllerAdvice.class);

    /**
     * Handles exceptions caused by invalid request data.
     *
     * @param ex the exception raised by Spring
     * @param request the request object
     * @return a response with HTTP status 400 and a JSON object containing errors
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
     * Handles exceptions caused by non-existent Pokemon Centers.
     *
     * @param ex the exception raised by Spring
     * @param request the request object
     * @return a response with HTTP status 404 and the exception message
     */
    @ExceptionHandler(PokemonCenterNotFoundException.class)
    public ResponseEntity<String> handlePokemonCenterNotFoundException(PokemonCenterNotFoundException ex, HttpServletRequest request) {
        logRequestException(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Logs an exception with the request information.
     *
     * @param ex the exception to log
     * @param request the request object
     */
    private static void logRequestException(Exception ex, HttpServletRequest request) {
        log.error("Exception for request [{} {}]: {}\n", request.getMethod(), request.getRequestURI(), ex.getMessage(), ex);
    }
}
