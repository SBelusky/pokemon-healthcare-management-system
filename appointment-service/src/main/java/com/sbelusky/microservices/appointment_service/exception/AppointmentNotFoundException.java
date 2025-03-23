package com.sbelusky.microservices.appointment_service.exception;

public class AppointmentNotFoundException extends Exception{
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
