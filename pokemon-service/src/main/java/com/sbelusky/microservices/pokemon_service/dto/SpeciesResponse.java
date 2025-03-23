package com.sbelusky.microservices.pokemon_service.dto;

public record SpeciesResponse(Long id, String name, String type, String weight, String photoImage, String baseHappiness, String captureRate, String color, String habitat, Boolean isMythical, String shape, String growthRate) {
}
