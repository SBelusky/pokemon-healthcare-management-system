package com.sbelusky.microservices.user_service.dto;

import java.time.LocalDate;
import java.util.Set;

public record PokemonResponse(Long id, String name, LocalDate birthDate, SpeciesResponse speciesResponse, Set<AbilityResponse> abilities) {
}
