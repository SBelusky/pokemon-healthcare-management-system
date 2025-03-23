package com.sbelusky.microservices.pokemon_center_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PokemonCenterRequest(
        @NotBlank(message = "Name is mandatory")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        String name,

        @NotBlank(message = "City is mandatory")
        @Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
        String city,

        @NotBlank(message = "Street is mandatory")
        @Size(min = 2, max = 100, message = "Street must be between 2 and 100 characters")
        String street,

        @NotBlank(message = "Country is mandatory")
        @Size(min = 2, max = 100, message = "Country must be between 2 and 100 characters")
        String country,

        @NotBlank(message = "Phone number is mandatory")
        @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
        String phoneNumber,

        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Specialization is mandatory")
        @Size(min = 3, max = 100, message = "Specialization must be between 3 and 100 characters")
        String specialization
) {
}