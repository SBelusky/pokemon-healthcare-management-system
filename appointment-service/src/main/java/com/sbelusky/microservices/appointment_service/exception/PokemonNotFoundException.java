package com.sbelusky.microservices.appointment_service.exception;

public class PokemonNotFoundException extends Exception {
    public PokemonNotFoundException(String message) {
        super(message);
    }
}
