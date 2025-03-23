package com.sbelusky.microservices.treatment_service.dto;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors) {
}
