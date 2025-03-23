package com.sbelusky.microservices.appointment_service.dto;

import com.sbelusky.microservices.appointment_service.model.enums.AppointmentStatus;

import java.time.LocalDateTime;

public record AppointmentResponse(
        LocalDateTime appointmentDate,
        String status,
        Long pokemonId,
        Long doctorId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
}
