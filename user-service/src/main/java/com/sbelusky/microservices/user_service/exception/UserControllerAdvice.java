package com.sbelusky.microservices.user_service.exception;

import com.sbelusky.microservices.user_service.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class provides a centralized exception handling mechanism for the REST API controllers of the User Service.
 * It is responsible for catching, logging and returning appropriate error responses for the various types of exceptions
 * that can occur during the execution of the controllers.
 */
@RestControllerAdvice
@Hidden
public class UserControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(UserControllerAdvice.class);

    /**
     * Handles validation exceptions that occur when the input data does not meet the constraints defined by the
     * validation annotations.
     *
     * @param ex     the exception that was thrown
     * @param request the HTTP request that triggered the exception
     * @return a ResponseEntity containing an ErrorResponse with the validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage,
                        (existing, _) -> existing));

        log.error("Validation exception for request [{} {}]: {}\nErrors: {}", request.getMethod(), request.getRequestURI(), ex.getMessage(), errors, ex);

        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles UserNotFoundExceptions that occur when the user with the given ID is not found.
     *
     * @param ex     the exception that was thrown
     * @param request the HTTP request that triggered the exception
     * @return a ResponseEntity with a 404 status and an error message
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        logRequestException(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles generic exceptions that are not handled by any of the other exception handlers.
     *
     * @param ex     the exception that was thrown
     * @param request the HTTP request that triggered the exception
     * @return a ResponseEntity with a 500 status and an error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex, HttpServletRequest request) {
        logRequestException(ex, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error. Please contact support.");
    }

    /**
     * Logs the given exception and its message.
     *
     * @param ex     the exception to log
     * @param request the HTTP request that triggered the exception
     */
    private static void logRequestException(Exception ex, HttpServletRequest request) {
        log.error("Exception for request [{} {}]: {}\n", request.getMethod(), request.getRequestURI(), ex.getMessage(), ex);
    }
}
