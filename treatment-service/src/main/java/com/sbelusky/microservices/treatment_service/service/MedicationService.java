package com.sbelusky.microservices.treatment_service.service;

import com.sbelusky.microservices.treatment_service.dto.MedicationRequest;
import com.sbelusky.microservices.treatment_service.dto.MedicationResponse;
import com.sbelusky.microservices.treatment_service.exception.MedicationNotFoundException;
import com.sbelusky.microservices.treatment_service.model.Medication;
import com.sbelusky.microservices.treatment_service.repository.MedicationRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service for managing medications.
 */
@Service
public class MedicationService {
    private MedicationRepository medicationRepository;
    private static final Logger log = LoggerFactory.getLogger(MedicationService.class);

    /**
     * Creates a new medication.
     *
     * @param medicationRequest the request containing the details of the medication to be created
     * @return the response containing the details of the created medication
     */
    public MedicationResponse createMedication(@Valid MedicationRequest medicationRequest) {
        Medication medication = getMedicationFromMedicationResponse(medicationRequest);
        medicationRepository.save(medication);

        return getMedicationResponseFromMedication(medication);
    }

    /**
     * Retrieves a medication by its ID.
     *
     * @param id the ID of the medication to retrieve
     * @return the response containing the details of the medication
     * @throws MedicationNotFoundException if no medication is found with the given ID
     */
    public MedicationResponse getMedicationById(Long id) throws MedicationNotFoundException {
        Medication medication = medicationRepository.findById(id).orElse(null);

        if(medication == null) {
            throw new MedicationNotFoundException("Medication not found with id: " + id);
        }

        return getMedicationResponseFromMedication(medication);
    }

    /**
     * Retrieves all medications.
     *
     * @return a set of medication responses containing details of all medications
     */
    public Set<MedicationResponse> getMedicationResponses() {
        return medicationRepository.findAll().stream()
                .map(MedicationService::getMedicationResponseFromMedication)
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves all medications.
     *
     * @return a set of medications
     */
    public Set<Medication> getMedications() {
        return new HashSet<>(medicationRepository.findAll());
    }

    /**
     * Maps a medication to its response.
     *
     * @param medication the medication to map
     * @return the response containing the details of the medication
     */
    public static MedicationResponse getMedicationResponseFromMedication(Medication medication) {
        return new MedicationResponse(
                medication.getId(),
                medication.getName(),
                medication.getDescription(),
                medication.getDosage(),
                medication.getPrice(),
                medication.getCurrency(),
                medication.getCreatedAt(),
                medication.getUpdatedAt()
        );
    }

    /**
     * Maps a medication request to its medication.
     *
     * @param medicationRequest the request containing the details of the medication to be created
     * @return the medication containing the details of the medication to be created
     */
    private static Medication getMedicationFromMedicationResponse(MedicationRequest medicationRequest) {
        return new Medication(
                medicationRequest.name(),
                medicationRequest.description(),
                medicationRequest.dosage(),
                medicationRequest.price(),
                medicationRequest.currency()
        );
    }
}