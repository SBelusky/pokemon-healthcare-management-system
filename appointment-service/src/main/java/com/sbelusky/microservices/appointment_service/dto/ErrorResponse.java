package com.sbelusky.microservices.appointment_service.dto;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors) {
}
