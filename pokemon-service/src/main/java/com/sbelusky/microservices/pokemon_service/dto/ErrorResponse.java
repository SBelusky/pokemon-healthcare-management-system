package com.sbelusky.microservices.pokemon_service.dto;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors) {
}
