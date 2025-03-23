package com.sbelusky.microservices.treatment_service.exception;

public class PokemonNotFoundException extends Exception {
    public PokemonNotFoundException(String message) {
        super(message);
    }
}
