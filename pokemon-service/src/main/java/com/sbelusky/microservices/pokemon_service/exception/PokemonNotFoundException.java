package com.sbelusky.microservices.pokemon_service.exception;

public class PokemonNotFoundException extends Exception {
    public PokemonNotFoundException(String message) {
        super(message);
    }
}
