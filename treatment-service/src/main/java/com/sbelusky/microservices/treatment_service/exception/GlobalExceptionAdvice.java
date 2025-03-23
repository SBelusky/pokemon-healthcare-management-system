package com.sbelusky.microservices.treatment_service.exception;

import com.sbelusky.microservices.treatment_service.dto.ErrorResponse;
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
/**
 * This class provides global exception handling for the treatment service.
 * It contains exception handlers for various types of exceptions that may occur in the service.
 * The handlers log the exception and return a response with the appropriate HTTP status code.
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    /**
     * Handles MethodArgumentNotValidException exceptions, which are thrown when the request body is invalid.
     * The method logs the exception and returns a response with the BAD_REQUEST HTTP status code.
     * The response contains an ErrorResponse object with the validation errors.
     * @param ex The exception that occurred.
     * @param request The HTTP request that caused the exception.
     * @return A ResponseEntity containing the ErrorResponse object with the validation errors.
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
     * Handles MedicationNotFoundException exceptions, which are thrown when a medication is not found.
     * The method logs the exception and returns a response with the NOT_FOUND HTTP status code.
     * The response contains the exception message.
     * @param ex The exception that occurred.
     * @param request The HTTP request that caused the exception.
     * @return A ResponseEntity containing the exception message.
     */
    @ExceptionHandler(MedicationNotFoundException.class)
    public ResponseEntity<String> handleMedicationNotFoundException(MedicationNotFoundException ex, HttpServletRequest request) {
        logRequestException(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles PokemonNotFoundException exceptions, which are thrown when a Pokémon is not found.
     * The method logs the exception and returns a response with the NOT_FOUND HTTP status code.
     * The response contains the exception message.
     * @param ex The exception that occurred.
     * @param request The HTTP request that caused the exception.
     * @return A ResponseEntity containing the exception message.
     */
    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<String> handlePokemonNotFoundException(PokemonNotFoundException ex, HttpServletRequest request) {
        logRequestException(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles TreatmentHistoryNotFound exceptions, which are thrown when a treatment history is not found.
     * The method logs the exception and returns a response with the NOT_FOUND HTTP status code.
     * The response contains the exception message.
     * @param ex The exception that occurred.
     * @param request The HTTP request that caused the exception.
     * @return A ResponseEntity containing the exception message.
     */
    @ExceptionHandler(TreatmentHistoryNotFound.class)
    public ResponseEntity<String> handleTreatmentHistoryNotFoundException(TreatmentHistoryNotFound ex, HttpServletRequest request) {
        logRequestException(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles UserHasNoPokemonException exceptions, which are thrown when a user has no Pokémon.
     * The method logs the exception and returns a response with the NOT_FOUND HTTP status code.
     * The response contains the exception message.
     * @param ex The exception that occurred.
     * @param request The HTTP request that caused the exception.
     * @return A ResponseEntity containing the exception message.
     */
    @ExceptionHandler(UserHasNoPokemonException.class)
    public ResponseEntity<String> handleUserHasNoPokemonException(UserHasNoPokemonException ex, HttpServletRequest request) {
        logRequestException(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles UserNotFoundException exceptions, which are thrown when a user is not found.
     * The method logs the exception and returns a response with the NOT_FOUND HTTP status code.
     * The response contains the exception message.
     * @param ex The exception that occurred.
     * @param request The HTTP request that caused the exception.
     * @return A ResponseEntity containing the exception message.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        logRequestException(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Logs the exception and the request that caused it.
     * @param ex The exception that occurred.
     * @param request The HTTP request that caused the exception.
     */
    private static void logRequestException(Exception ex, HttpServletRequest request) {
        log.error("Exception for request [{} {}]: {}\n", request.getMethod(), request.getRequestURI(), ex.getMessage(), ex);
    }
}
