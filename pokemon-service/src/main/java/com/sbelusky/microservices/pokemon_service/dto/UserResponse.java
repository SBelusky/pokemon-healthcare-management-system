package com.sbelusky.microservices.pokemon_service.dto;

import java.time.LocalDateTime;

public record UserResponse(String userName, String email, String firstName, String lastName, String role, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
