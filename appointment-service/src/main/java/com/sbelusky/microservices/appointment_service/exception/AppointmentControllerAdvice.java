package com.sbelusky.microservices.appointment_service.exception;

import com.sbelusky.microservices.appointment_service.dto.ErrorResponse;
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

/**
 * Global exception handler for the Appointment service.
 * <p>
 * Handles the following exceptions:
 * <ul>
 * <li> {@link MethodArgumentNotValidException} - thrown by Spring when validation of method arguments fails
 * <li> {@link AppointmentNotFoundException} - thrown when an appointment with the given ID is not found
 * <li> {@link PokemonNotFoundException} - thrown when a pokemon with the given ID is not found
 * <li> {@link UsertNotFoundException} - thrown when a user with the given ID is not found
 * </ul>
 */
@Hidden
@RestControllerAdvice
public class AppointmentControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(AppointmentControllerAdvice.class);

    /**
     * Handles {@link MethodArgumentNotValidException} thrown by Spring when validation of method arguments fails.
     * <p>
     * Logs the error and returns a response with HTTP status code 400 (Bad Request) and a JSON body containing the validation errors.
     *
     * @param ex the exception
     * @param request the HTTP request
     * @return the response
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
     * Handles {@link AppointmentNotFoundException} thrown when an appointment with the given ID is not found.
     * <p>
     * Logs the error and returns a response with HTTP status code 404 (Not Found) and a JSON body containing the error message.
     *
     * @param ex the exception
     * @param request the HTTP request
     * @return the response
     */
    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<String> handleAppointmentNotFoundException(AppointmentNotFoundException ex, HttpServletRequest request) {
        logRequestException(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles {@link PokemonNotFoundException} thrown when a pokemon with the given ID is not found.
     * <p>
     * Logs the error and returns a response with HTTP status code 404 (Not Found) and a JSON body containing the error message.
     *
     * @param ex the exception
     * @param request the HTTP request
     * @return the response
     */
    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<String> handlePokemonNotFoundException(PokemonNotFoundException ex, HttpServletRequest request) {
        logRequestException(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles {@link UsertNotFoundException} thrown when a user with the given ID is not found.
     * <p>
     * Logs the error and returns a response with HTTP status code 404 (Not Found) and a JSON body containing the error message.
     *
     * @param ex the exception
     * @param request the HTTP request
     * @return the response
     */
    @ExceptionHandler(UsertNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UsertNotFoundException ex, HttpServletRequest request) {
        logRequestException(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    private static void logRequestException(Exception ex, HttpServletRequest request) {
        log.error("Exception for request [{} {}]: {}\n", request.getMethod(), request.getRequestURI(), ex.getMessage(), ex);
    }
}
