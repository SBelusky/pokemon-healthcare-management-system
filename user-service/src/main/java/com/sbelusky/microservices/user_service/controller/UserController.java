package com.sbelusky.microservices.user_service.controller;

import com.sbelusky.microservices.user_service.dto.UserRequest;
import com.sbelusky.microservices.user_service.dto.UserResponse;
import com.sbelusky.microservices.user_service.exception.UserNotFoundException;
import com.sbelusky.microservices.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user.
     * <p>
     * This endpoint allows clients to create a new user by providing the necessary user details.
     *
     * @param userRequest contains information about the User to be created
     * @return UserResponse containing information about the created User
     */
    @Operation(summary = "Create a new user", description = "Creates a new user with the provided details.")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Parameter(description = "User request containing user details", required = true) @Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    /**
     * Retrieves a user by email.
     * <p>
     * This endpoint allows clients to retrieve a user by their email address.
     *
     * @param email the email of the User to be retrieved
     * @return UserResponse containing information about the retrieved User
     * @throws UserNotFoundException if no user is found with the provided email
     */
    @Operation(summary = "Get user by email", description = "Retrieves user details using the provided email address.")
    @ApiResponse(responseCode = "200", description = "User retrieved successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserByEmail(@Parameter(description = "Email of the user to retrieve", required = true) @RequestParam String email) throws UserNotFoundException {
        return userService.getUserByEmail(email);
    }

    /**
     * Retrieves a user by ID.
     * <p>
     * This endpoint allows clients to retrieve a user by their unique ID.
     *
     * @param id the ID of the User to be retrieved
     * @return UserResponse containing information about the retrieved User
     * @throws UserNotFoundException if no user is found with the provided ID
     */
    @Operation(summary = "Get user by ID", description = "Retrieves user details using the provided user ID.")
    @ApiResponse(responseCode = "200", description = "User retrieved successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@Parameter(description = "ID of the user to retrieve", required = true) @PathVariable Long id) throws UserNotFoundException {
        return userService.getUserById(id);
    }
}