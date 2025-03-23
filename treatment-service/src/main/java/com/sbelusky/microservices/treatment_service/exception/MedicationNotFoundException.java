package com.sbelusky.microservices.treatment_service.exception;

public class MedicationNotFoundException extends Exception {
    public MedicationNotFoundException(String message) {
        super(message);
    }
}
