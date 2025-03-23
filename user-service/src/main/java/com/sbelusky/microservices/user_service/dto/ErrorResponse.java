package com.sbelusky.microservices.user_service.dto;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors) {
}
