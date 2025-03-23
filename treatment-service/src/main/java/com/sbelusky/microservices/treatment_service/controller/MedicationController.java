package com.sbelusky.microservices.treatment_service.controller;

import com.sbelusky.microservices.treatment_service.dto.MedicationRequest;
import com.sbelusky.microservices.treatment_service.dto.MedicationResponse;
import com.sbelusky.microservices.treatment_service.exception.MedicationNotFoundException;
import com.sbelusky.microservices.treatment_service.service.MedicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/medications")
public class MedicationController {
    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    /**
     * Creates a new medication entry.
     *
     * @param medicationRequest the medication request containing details of the medication to be created
     * @return the response containing the details of the created medication
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Medication", description = "Creates a new medication and returns the created medication details.")
    @ApiResponse(responseCode = "201", description = "Medication successfully created")
    public MedicationResponse createMedication(@Valid @RequestBody MedicationRequest medicationRequest) {
        return medicationService.createMedication(medicationRequest);
    }

    /**
     * Retrieves all medications.
     *
     * @return a set of medication responses containing details of all medications
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All Medications", description = "Retrieves a list of all medications.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all medications")
    public Set<MedicationResponse> getAllMedications() {
        return medicationService.getMedicationResponses();
    }

    /**
     * Retrieves a medication by its ID.
     *
     * @param id the ID of the medication to retrieve
     * @return the response containing the details of the medication
     * @throws MedicationNotFoundException if no medication is found with the given ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Medication by ID", description = "Retrieves a medication by its ID.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the medication")
    @ApiResponse(responseCode = "404", description = "Medication not found")
    public MedicationResponse getMedicationById(@PathVariable("id") Long id) throws MedicationNotFoundException {
        return medicationService.getMedicationById(id);
    }
}