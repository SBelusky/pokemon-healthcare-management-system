package com.sbelusky.microservices.treatment_service.controller;

import com.sbelusky.microservices.treatment_service.dto.PrescriptionRequest;
import com.sbelusky.microservices.treatment_service.dto.PrescriptionResponse;
import com.sbelusky.microservices.treatment_service.exception.MedicationNotFoundException;
import com.sbelusky.microservices.treatment_service.exception.PokemonNotFoundException;
import com.sbelusky.microservices.treatment_service.exception.TreatmentHistoryNotFound;
import com.sbelusky.microservices.treatment_service.exception.UserNotFoundException;
import com.sbelusky.microservices.treatment_service.service.PrescriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * REST API controller for managing prescriptions.
 */
@RestController
@RequestMapping("/api/prescriptions")
@Tag(name = "Prescription", description = "Prescription management endpoints")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    /**
     * Creates a new prescription.
     * @param prescriptionRequest description, doctorId, pokemonId and medicationIds
     * @return created prescription
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create prescription", description = "Create a new prescription for a pokemon")
    public PrescriptionResponse createSurgery(@Valid @RequestBody PrescriptionRequest prescriptionRequest) throws UserNotFoundException, PokemonNotFoundException, MedicationNotFoundException {
        return prescriptionService.createPrescription(prescriptionRequest);
    }

    /**
     * Returns a set of prescriptions for a given pokemon.
     * @param pokemonId pokemon id
     * @return set of prescriptions
     */
    @GetMapping("/pokemon/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get prescriptions for pokemon", description = "Get a set of prescriptions for a pokemon")
    public Set<PrescriptionResponse> getPrescriptionByPokemonId(
            @Parameter(description = "pokemon id") @PathVariable("id") Long pokemonId) throws PokemonNotFoundException, TreatmentHistoryNotFound {
        return prescriptionService.getPrescriptionByPokemonId(pokemonId);
    }

    /**
     * Returns a set of prescriptions for a given doctor.
     * @param doctorId doctor id
     * @return set of prescriptions
     */
    @GetMapping("/doctor/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get prescriptions for doctor", description = "Get a set of prescriptions for a doctor")
    public Set<PrescriptionResponse> getPrescriptionByDoctorId(
            @Parameter(description = "doctor id") @PathVariable("id") Long doctorId) throws UserNotFoundException {
        return prescriptionService.getPrescriptionByDoctorId(doctorId);
    }
}
