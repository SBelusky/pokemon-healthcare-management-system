package com.sbelusky.microservices.appointment_service.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record PokemonResponse(String name, LocalDate birthDate, SpeciesResponse speciesResponse, Set<AbilityResponse> abilities, LocalDateTime createdAt, LocalDateTime updatedAt) {

}