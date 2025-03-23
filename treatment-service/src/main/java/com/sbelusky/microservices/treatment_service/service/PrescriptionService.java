package com.sbelusky.microservices.treatment_service.service;

import com.sbelusky.microservices.treatment_service.client.PokemonClient;
import com.sbelusky.microservices.treatment_service.client.UserClient;
import com.sbelusky.microservices.treatment_service.dto.*;
import com.sbelusky.microservices.treatment_service.exception.MedicationNotFoundException;
import com.sbelusky.microservices.treatment_service.exception.PokemonNotFoundException;
import com.sbelusky.microservices.treatment_service.exception.TreatmentHistoryNotFound;
import com.sbelusky.microservices.treatment_service.exception.UserNotFoundException;
import com.sbelusky.microservices.treatment_service.model.Medication;
import com.sbelusky.microservices.treatment_service.model.Prescription;
import com.sbelusky.microservices.treatment_service.model.TreatmentHistory;
import com.sbelusky.microservices.treatment_service.repository.PrescriptionRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for managing prescriptions.
 */
@Service
public class PrescriptionService {
    private final UserClient userClient;
    private final PokemonClient pokemonClient;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicationService medicationService;
    private final TreatmentHistoryService historyService;

    /**
     * Constructor for PrescriptionService.
     *
     * @param historyService           Treatment history service.
     * @param medicationService        Medication service.
     * @param prescriptionRepository   Prescription repository.
     * @param pokemonClient            Pokemon client.
     * @param userClient               User client.
     */
    public PrescriptionService(TreatmentHistoryService historyService, MedicationService medicationService, PrescriptionRepository prescriptionRepository, PokemonClient pokemonClient, UserClient userClient) {
        this.historyService = historyService;
        this.medicationService = medicationService;
        this.prescriptionRepository = prescriptionRepository;
        this.pokemonClient = pokemonClient;
        this.userClient = userClient;
    }

    /**
     * Creates a new prescription.
     *
     * @param prescriptionRequest Data for creating the prescription.
     * @return Created prescription response.
     * @throws UserNotFoundException       If the doctor is not found.
     * @throws PokemonNotFoundException    If the pokemon is not found.
     * @throws MedicationNotFoundException If any medication is not found.
     */
    public PrescriptionResponse createPrescription(@Valid PrescriptionRequest prescriptionRequest) throws UserNotFoundException, PokemonNotFoundException, MedicationNotFoundException {
        UserResponse userResponse = userClient.getUserById(prescriptionRequest.doctorId());
        PokemonResponse pokemonResponse = pokemonClient.getPokemonById(prescriptionRequest.pokemonId());

        if (userResponse == null) {
            throw new UserNotFoundException("User not found with id: " + prescriptionRequest.doctorId());
        } else if (pokemonResponse == null) {
            throw new PokemonNotFoundException("Pokemon not found with id:" + prescriptionRequest.pokemonId());
        }

        Set<Medication> medications = medicationService.getMedications().stream()
                .filter(medication -> prescriptionRequest.medicationIds().contains(medication.getId()))
                .collect(Collectors.toSet());

        if (medications.size() != prescriptionRequest.medicationIds().size()) {
            throw new MedicationNotFoundException("Some or all medications cannot be found");
        }

        Prescription prescription = getPrescriptionFromPrescriptionRequest(prescriptionRequest, medications);

        if (!historyService.hasPokemonTreatmentHistory(prescriptionRequest.pokemonId())) {
            TreatmentHistory treatmentHistory = historyService.createTreatmentHistory(prescriptionRequest.pokemonId());
            prescription.setTreatmentHistory(treatmentHistory);
        } else {
            prescription.setTreatmentHistory(historyService.getTreatmentHistory(prescriptionRequest.pokemonId()));
        }

        prescriptionRepository.save(prescription);

        return getPrescriptionResponseFromPrescription(prescription);
    }

    /**
     * Retrieves prescriptions for a specific pokemon.
     *
     * @param pokemonId ID of the pokemon.
     * @return Set of prescription responses.
     * @throws PokemonNotFoundException If the pokemon is not found.
     * @throws TreatmentHistoryNotFound If the treatment history is not found.
     */
    public Set<PrescriptionResponse> getPrescriptionByPokemonId(Long pokemonId) throws PokemonNotFoundException, TreatmentHistoryNotFound {
        PokemonResponse pokemonResponse = pokemonClient.getPokemonById(pokemonId);

        if (pokemonResponse == null) {
            throw new PokemonNotFoundException("Pokemon not found with id:" + pokemonId);
        }

        return historyService.getTreatmentHistoryByPokemonId(pokemonId).prescriptions();
    }

    /**
     * Retrieves prescriptions for a specific doctor.
     *
     * @param doctorId ID of the doctor.
     * @return Set of prescription responses.
     * @throws UserNotFoundException If the doctor is not found.
     */
    public Set<PrescriptionResponse> getPrescriptionByDoctorId(Long doctorId) throws UserNotFoundException {
        UserResponse userResponse = userClient.getUserById(doctorId);

        if (userResponse == null) {
            throw new UserNotFoundException("User not found with id: " + doctorId);
        }

        Example<Prescription> example = Example.of(new Prescription());
        example.getProbe().setDoctorId(doctorId);

        return prescriptionRepository.findAll(example).stream()
                .sorted(Comparator.comparing(Prescription::getCreatedAt).reversed())
                .map(PrescriptionService::getPrescriptionResponseFromPrescription)
                .collect(Collectors.toSet());
    }

    /**
     * Converts a Prescription to a PrescriptionResponse.
     *
     * @param prescription The prescription to convert.
     * @return The converted PrescriptionResponse.
     */
    public static PrescriptionResponse getPrescriptionResponseFromPrescription(Prescription prescription) {
        Set<MedicationResponse> medications = prescription.getMedications().stream()
                .map(MedicationService::getMedicationResponseFromMedication)
                .collect(Collectors.toSet());

        return new PrescriptionResponse(
                prescription.getDescription(),
                prescription.getDoctorId(),
                medications,
                prescription.getCreatedAt(),
                prescription.getUpdatedAt()
        );
    }

    /**
     * Converts a PrescriptionRequest to a Prescription.
     *
     * @param prescriptionRequest The prescription request.
     * @param medications         Set of medications.
     * @return The created Prescription.
     */
    private static Prescription getPrescriptionFromPrescriptionRequest(PrescriptionRequest prescriptionRequest, Set<Medication> medications) {
        return new Prescription(
                prescriptionRequest.description(),
                prescriptionRequest.doctorId(),
                medications);
    }
}