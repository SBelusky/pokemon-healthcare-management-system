package com.sbelusky.microservices.appointment_service.exception;

public class UsertNotFoundException extends Exception{
    public UsertNotFoundException(String message) {
        super(message);
    }
}
