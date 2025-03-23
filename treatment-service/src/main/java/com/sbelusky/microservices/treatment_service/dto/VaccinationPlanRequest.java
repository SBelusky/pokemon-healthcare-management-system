package com.sbelusky.microservices.treatment_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public record VaccinationPlanRequest(
        @NotBlank(message = "Type is mandatory")
        String type,

        @NotBlank(message = "Description is mandatory")
        String description,

        @NotNull(message = "Vaccination date is mandatory")
        LocalDateTime vaccinationDate,

        @NotNull(message = "Pokemon ID is mandatory")
        @Positive(message = "Pokemon ID must be positive")
        Long pokemonId,

        @NotNull(message = "Doctor ID is mandatory")
        @Positive(message = "Doctor ID must be positive")
        Long doctorId
) {
}