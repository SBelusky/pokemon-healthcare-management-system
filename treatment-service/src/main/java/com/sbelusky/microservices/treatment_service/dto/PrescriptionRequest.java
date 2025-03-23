package com.sbelusky.microservices.treatment_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Set;

public record PrescriptionRequest(
        @NotBlank(message = "Description is mandatory")
        String description,

        @NotNull(message = "Pokemon ID is mandatory")
        @Positive(message = "Pokemon ID must be positive")
        Long pokemonId,

        @NotNull(message = "Doctor ID is mandatory")
        @Positive(message = "Doctor ID must be positive")
        Long doctorId,

        @NotNull(message = "Medication IDs are mandatory")
        Set<@Positive(message = "Medication ID must be positive") Long> medicationIds,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}