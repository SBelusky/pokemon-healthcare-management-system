package com.sbelusky.microservices.treatment_service.service;

import com.sbelusky.microservices.treatment_service.client.PokemonClient;
import com.sbelusky.microservices.treatment_service.client.UserClient;
import com.sbelusky.microservices.treatment_service.dto.*;
import com.sbelusky.microservices.treatment_service.exception.PokemonNotFoundException;
import com.sbelusky.microservices.treatment_service.exception.TreatmentHistoryNotFound;
import com.sbelusky.microservices.treatment_service.exception.UserHasNoPokemonException;
import com.sbelusky.microservices.treatment_service.exception.UserNotFoundException;
import com.sbelusky.microservices.treatment_service.model.Prescription;
import com.sbelusky.microservices.treatment_service.model.Surgery;
import com.sbelusky.microservices.treatment_service.model.TreatmentHistory;
import com.sbelusky.microservices.treatment_service.model.VaccinationPlan;
import com.sbelusky.microservices.treatment_service.repository.TreatmentHistoryRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service for managing treatment histories associated with pokemons.
 */
@Service
public class TreatmentHistoryService {
    private final TreatmentHistoryRepository historyRepository;
    private final PokemonClient pokemonClient;
    private final UserClient userClient;

    public TreatmentHistoryService(TreatmentHistoryRepository historyRepository, PokemonClient pokemonClient, UserClient userClient) {
        this.historyRepository = historyRepository;
        this.pokemonClient = pokemonClient;
        this.userClient = userClient;
    }

    /**
     * Creates a new treatment history for a given pokemon.
     *
     * @param pokemonId the ID of the pokemon
     * @return the created TreatmentHistory
     */
    public TreatmentHistory createTreatmentHistory(Long pokemonId) {
        TreatmentHistory treatmentHistory = new TreatmentHistory(pokemonId);
        return historyRepository.save(treatmentHistory);
    }

    /**
     * Retrieves the treatment history for a given pokemon.
     *
     * @param pokemonId the ID of the pokemon
     * @return the TreatmentHistory
     */
    public TreatmentHistory getTreatmentHistory(Long pokemonId) {
        return findTreatmentHistoryByPokemonId(pokemonId);
    }

    /**
     * Retrieves the treatment history response for a pokemon by ID.
     *
     * @param pokemonId the ID of the pokemon
     * @return the TreatmentHistoryResponse
     * @throws PokemonNotFoundException if the pokemon is not found
     * @throws TreatmentHistoryNotFound if the treatment history is not found
     */
    public TreatmentHistoryResponse getTreatmentHistoryByPokemonId(Long pokemonId) throws PokemonNotFoundException, TreatmentHistoryNotFound {
        if (pokemonClient.getPokemonById(pokemonId) == null) {
            throw new PokemonNotFoundException("Pokemon not found with id: " + pokemonId);
        }

        Example<TreatmentHistory> example = Example.of(new TreatmentHistory());
        example.getProbe().setPokemonId(pokemonId);

        TreatmentHistory treatmentHistory = historyRepository.findOne(example)
                .orElseThrow(() -> new TreatmentHistoryNotFound("Pokemon with id: " + pokemonId + " has no treatment history"));

        treatmentHistory.getSurgeries().sort(Comparator.comparing(Surgery::getSurgeryDate));
        treatmentHistory.getPrescriptions().sort(Comparator.comparing(Prescription::getCreatedAt));
        treatmentHistory.getVaccinationPlans().sort(Comparator.comparing(VaccinationPlan::getVaccinationDate));

        return getTreatmentHistoryResponseByTreatmentHistory(treatmentHistory);
    }

    /**
     * Retrieves treatment histories for a user by their ID.
     *
     * @param ownerId the ID of the user
     * @return a set of TreatmentHistoryResponse
     * @throws UserNotFoundException if the user is not found
     * @throws UserHasNoPokemonException if the user has no pokemons
     */
    public Set<TreatmentHistoryResponse> getTreatmentHistoryByOwnerId(Long ownerId) throws UserNotFoundException, UserHasNoPokemonException {
        UserResponse userResponse = userClient.getUserById(ownerId);
        Set<PokemonResponse> pokemonResponses = pokemonClient.getPokemonsByOwnerId(ownerId);

        if (userResponse == null) {
            throw new UserNotFoundException("User not found with id: " + ownerId);
        } else if (pokemonResponses.isEmpty()) {
            throw new UserHasNoPokemonException("User has no pokemons");
        }

        return pokemonResponses.stream()
                .map(pokemonResponse -> {
                    Example<TreatmentHistory> example = Example.of(new TreatmentHistory());
                    example.getProbe().setPokemonId(pokemonResponse.id());

                    TreatmentHistory treatmentHistory = historyRepository.findOne(example).orElse(null);

                    return (treatmentHistory != null) ? getTreatmentHistoryResponseByTreatmentHistory(treatmentHistory) : null;
                })
                .collect(Collectors.toSet());
    }

    /**
     * Converts a TreatmentHistory entity to a TreatmentHistoryResponse DTO.
     *
     * @param treatmentHistory the TreatmentHistory entity
     * @return the TreatmentHistoryResponse DTO
     */
    private TreatmentHistoryResponse getTreatmentHistoryResponseByTreatmentHistory(TreatmentHistory treatmentHistory) {
        Set<SurgeryResponse> surgeries = treatmentHistory.getSurgeries().stream()
                .map(SurgeryService::getSurgeryResponseFromSurgery)
                .collect(Collectors.toSet());

        Set<PrescriptionResponse> prescriptions = treatmentHistory.getPrescriptions().stream()
                .map(PrescriptionService::getPrescriptionResponseFromPrescription)
                .collect(Collectors.toSet());

        Set<VaccinationPlanResponse> vaccinationPlans = treatmentHistory.getVaccinationPlans().stream()
                .map(VaccinationPlanService::getVaccinationPlanResponseFromVaccinationPlan)
                .collect(Collectors.toSet());

        return new TreatmentHistoryResponse(
                treatmentHistory.getPokemonId(),
                surgeries,
                prescriptions,
                vaccinationPlans,
                treatmentHistory.getCreatedAt(),
                treatmentHistory.getUpdatedAt()
        );
    }

    /**
     * Checks if a pokemon has a treatment history.
     *
     * @param pokemonId the ID of the pokemon
     * @return true if the pokemon has a treatment history, false otherwise
     */
    public boolean hasPokemonTreatmentHistory(Long pokemonId) {
        return findTreatmentHistoryByPokemonId(pokemonId) != null;
    }

    /**
     * Finds a treatment history by pokemon ID.
     *
     * @param pokemonId the ID of the pokemon
     * @return the TreatmentHistory if found, null otherwise
     */
    private TreatmentHistory findTreatmentHistoryByPokemonId(Long pokemonId) {
        Example<TreatmentHistory> example = Example.of(new TreatmentHistory());
        example.getProbe().setPokemonId(pokemonId);

        return historyRepository.findOne(example).orElse(null);
    }
}