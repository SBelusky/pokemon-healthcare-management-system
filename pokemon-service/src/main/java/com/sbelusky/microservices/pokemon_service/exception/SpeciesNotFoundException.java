package com.sbelusky.microservices.pokemon_service.exception;

public class SpeciesNotFoundException extends Exception{
    public SpeciesNotFoundException(String message) {
        super(message);
    }
}
