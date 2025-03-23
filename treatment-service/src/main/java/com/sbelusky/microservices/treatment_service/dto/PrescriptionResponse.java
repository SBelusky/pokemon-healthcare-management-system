package com.sbelusky.microservices.treatment_service.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record PrescriptionResponse(
        String description,
        Long doctorId,
        Set<MedicationResponse> medications,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
