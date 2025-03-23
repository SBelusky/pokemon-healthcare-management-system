package com.sbelusky.microservices.treatment_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MedicationRequest(
        @NotBlank(message = "Medication name is mandatory")
        @Size(min = 3, max = 100, message = "Medication name must be between 3 and 100 characters")
        String name,

        @Size(max = 255, message = "Description must be less than 255 characters")
        String description,

        @Size(max = 50, message = "Dosage must be less than 50 characters")
        String dosage,

        @NotNull(message = "Price is mandatory")
        @Positive(message = "Price must be positive")
        Double price,

        @Size(max = 3, message = "Currency must be a 3-letter code")
        String currency
) {
}