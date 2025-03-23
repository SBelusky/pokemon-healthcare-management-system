package com.sbelusky.microservices.pokemon_center_service.exception;

public class PokemonCenterNotFoundException extends Exception{
    public PokemonCenterNotFoundException(String message) {
        super(message);
    }
}
