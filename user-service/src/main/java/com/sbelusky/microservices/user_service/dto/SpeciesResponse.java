package com.sbelusky.microservices.user_service.dto;

public record SpeciesResponse(String name, String type, String weight, String photoImage, String baseHappiness, String captureRate, String color, String habitat, Boolean isMythical, String shape, String growthRate) {
}
