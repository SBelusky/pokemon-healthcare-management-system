package com.sbelusky.microservices.treatment_service.dto;

import java.time.LocalDateTime;

public record VaccinationPlanResponse(
        String type,
        String description,
        LocalDateTime vaccinationDate,
        Long doctorId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
