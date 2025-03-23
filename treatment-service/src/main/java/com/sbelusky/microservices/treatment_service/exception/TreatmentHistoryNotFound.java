package com.sbelusky.microservices.treatment_service.exception;

public class TreatmentHistoryNotFound extends Exception {
    public TreatmentHistoryNotFound(String message) {
        super(message);
    }
}
