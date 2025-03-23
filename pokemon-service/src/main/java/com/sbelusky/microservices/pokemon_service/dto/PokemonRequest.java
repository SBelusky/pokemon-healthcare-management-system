package com.sbelusky.microservices.pokemon_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Set;

public record PokemonRequest(
        Long id,

        @NotBlank(message = "Name is mandatory")
        String name,

        @NotNull(message = "Birth date is mandatory")
        LocalDate birthDate,

        @NotNull(message = "Species ID is mandatory")
        @Positive(message = "Species ID must be positive")
        Long speciesId,

        @NotNull(message = "Abilities are mandatory")
        Set<AbilityRequest> abilities,

        @NotNull(message = "Owner ID is mandatory")
        @Positive(message = "Owner ID must be positive")
        Long ownerId
) {
}