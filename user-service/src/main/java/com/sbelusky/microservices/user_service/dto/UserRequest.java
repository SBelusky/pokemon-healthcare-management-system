package com.sbelusky.microservices.user_service.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Username is mandatory")
        @Size(min = 3, max = 30, message = "User name must be between 3 and 30 characters")
        String userName,

        @NotBlank(message = "Email is mandatory")
        @Email(message = "Invalid email")
        String email,

        @NotBlank(message = "First name is mandatory")
        String firstName,

        @NotBlank(message = "Last name is mandatory")
        String lastName,

        @NotBlank(message = "Role is mandatory")
        String role
    ) {
}
