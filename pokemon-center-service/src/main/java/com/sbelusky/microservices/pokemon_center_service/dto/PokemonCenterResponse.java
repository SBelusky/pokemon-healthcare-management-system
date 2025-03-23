package com.sbelusky.microservices.pokemon_center_service.dto;

import java.time.LocalDateTime;

public record PokemonCenterResponse(String name, String city, String street, String country, String phoneNumber, String email, String specialization, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
