package com.sbelusky.microservices.treatment_service.exception;

public class UserHasNoPokemonException extends Exception{
    public UserHasNoPokemonException(String message) {
        super(message);
    }
}
