package com.sbelusky.microservices.pokemon_service.exception;

public class AbilityNotFoundException extends Exception {
    public AbilityNotFoundException(String message) {
        super(message);
    }
}
