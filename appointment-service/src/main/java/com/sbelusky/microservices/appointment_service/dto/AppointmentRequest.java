package com.sbelusky.microservices.appointment_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public record AppointmentRequest(
        Long id,

        @NotNull(message = "Appointment date is mandatory")
        LocalDateTime appointmentDate,

        @NotBlank(message = "Status is mandatory")
        String status,

        @NotNull(message = "Pokemon ID is mandatory")
        @Positive(message = "Pokemon ID must be positive")
        Long pokemonId,

        @NotNull(message = "Doctor ID is mandatory")
        @Positive(message = "Doctor ID must be positive")
        Long doctorId
) {
}