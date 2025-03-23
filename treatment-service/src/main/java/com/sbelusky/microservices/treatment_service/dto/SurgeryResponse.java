package com.sbelusky.microservices.treatment_service.dto;

import java.time.LocalDateTime;

public record SurgeryResponse(String description, LocalDateTime surgeryDate, Long doctorId, Long pokemonId, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
