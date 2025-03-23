package com.sbelusky.microservices.treatment_service.dto;

import java.time.LocalDateTime;

public record MedicationResponse(
    Long id,
    String name,
    String description,
    String dosage,
    Double price,
    String currency,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {
}
