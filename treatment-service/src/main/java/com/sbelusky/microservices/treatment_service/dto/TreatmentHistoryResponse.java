package com.sbelusky.microservices.treatment_service.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record TreatmentHistoryResponse(
        Long pokemonId,
        Set<SurgeryResponse> surgeries,
        Set<PrescriptionResponse> prescriptions,
        Set<VaccinationPlanResponse> vaccinations,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
