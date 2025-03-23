package com.sbelusky.microservices.treatment_service.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record PokemonResponse(Long id, String name, LocalDate birthDate, SpeciesResponse speciesResponse, Set<AbilityResponse> abilities, LocalDateTime createdAt, LocalDateTime updatedAt) {

}
