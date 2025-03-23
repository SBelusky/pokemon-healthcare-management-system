package com.sbelusky.microservices.treatment_service.service;

import com.sbelusky.microservices.treatment_service.client.PokemonClient;
import com.sbelusky.microservices.treatment_service.client.UserClient;
import com.sbelusky.microservices.treatment_service.dto.*;
import com.sbelusky.microservices.treatment_service.exception.PokemonNotFoundException;
import com.sbelusky.microservices.treatment_service.exception.TreatmentHistoryNotFound;
import com.sbelusky.microservices.treatment_service.exception.UserNotFoundException;
import com.sbelusky.microservices.treatment_service.model.TreatmentHistory;
import com.sbelusky.microservices.treatment_service.model.VaccinationPlan;
import com.sbelusky.microservices.treatment_service.repository.VaccinationPlanRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service for managing vaccination plans.
 */
@Service
public class VaccinationPlanService {
    private final UserClient userClient;
    private final PokemonClient pokemonClient;
    private final VaccinationPlanRepository vaccinationPlanRepository;
    private final TreatmentHistoryService historyService;

    /**
     * Constructor for VaccinationPlanService.
     *
     * @param userClient            User client for getting user data.
     * @param pokemonClient        Pokemon client for getting pokemon data.
     * @param vaccinationPlanRepository Repository for vaccination plans.
     * @param historyService        Service for managing treatment history.
     */
    public VaccinationPlanService(UserClient userClient, PokemonClient pokemonClient, VaccinationPlanRepository vaccinationPlanRepository, TreatmentHistoryService historyService) {
        this.userClient = userClient;
        this.pokemonClient = pokemonClient;
        this.vaccinationPlanRepository = vaccinationPlanRepository;
        this.historyService = historyService;
    }

    /**
     * Creates a new vaccination plan.
     *
     * @param vaccinationPlanRequest Data for creating the vaccination plan.
     * @return Created vaccination plan response.
     * @throws UserNotFoundException       If the doctor is not found.
     * @throws PokemonNotFoundException    If the pokemon is not found.
     */
    public VaccinationPlanResponse createVaccinationPlan(@Valid VaccinationPlanRequest vaccinationPlanRequest) throws UserNotFoundException, PokemonNotFoundException {
        UserResponse userResponse = userClient.getUserById(vaccinationPlanRequest.doctorId());
        PokemonResponse pokemonResponse = pokemonClient.getPokemonById(vaccinationPlanRequest.pokemonId());

        if(userResponse == null) {
            throw new UserNotFoundException("User not found with id: " + vaccinationPlanRequest.doctorId());
        }else if(pokemonResponse == null){
            throw new PokemonNotFoundException("Pokemon not found with id:" + vaccinationPlanRequest.pokemonId());
        }

        VaccinationPlan vaccinationPlan = getVaccinationPlanFromVaccinationPlanRequest(vaccinationPlanRequest);

        if(!historyService.hasPokemonTreatmentHistory(vaccinationPlanRequest.pokemonId())){
            TreatmentHistory treatmentHistory = historyService.createTreatmentHistory(vaccinationPlanRequest.pokemonId());
            vaccinationPlan.setTreatmentHistory(treatmentHistory);
        }else{
            vaccinationPlan.setTreatmentHistory(historyService.getTreatmentHistory(vaccinationPlanRequest.pokemonId()));
        }

        return getVaccinationPlanResponseFromVaccinationPlan(vaccinationPlanRepository.save(vaccinationPlan));
    }

    /**
     * Retrieves vaccination plans for a specific pokemon.
     *
     * @param pokemonId ID of the pokemon.
     * @return Set of vaccination plan responses.
     * @throws PokemonNotFoundException If the pokemon is not found.
     * @throws TreatmentHistoryNotFound If the treatment history is not found.
     */
    public Set<VaccinationPlanResponse> getVaccinationPlansByPokemonId(Long pokemonId) throws PokemonNotFoundException, TreatmentHistoryNotFound {
        return historyService.getTreatmentHistoryByPokemonId(pokemonId).vaccinations();
    }

    /**
     * Retrieves vaccination plans for a specific doctor.
     *
     * @param doctorId ID of the doctor.
     * @return Set of vaccination plan responses.
     * @throws UserNotFoundException If the doctor is not found.
     */
    public Set<VaccinationPlanResponse> getVaccinationPlansByDoctorId(Long doctorId) throws UserNotFoundException {
        UserResponse userResponse = userClient.getUserById(doctorId);

        if(userResponse == null) {
            throw new UserNotFoundException("User not found with id: " + doctorId);
        }

        Example<VaccinationPlan> example = Example.of(new VaccinationPlan());
        example.getProbe().setDoctorId(doctorId);

        return vaccinationPlanRepository.findAll(example).stream()
                .sorted(Comparator.comparing(VaccinationPlan::getVaccinationDate).reversed())
                .map(VaccinationPlanService::getVaccinationPlanResponseFromVaccinationPlan)
                .collect(Collectors.toSet());
    }

    /**
     * Converts a VaccinationPlan to a VaccinationPlanResponse.
     *
     * @param vaccinationPlan The vaccination plan to convert.
     * @return The converted VaccinationPlanResponse.
     */
    public static VaccinationPlanResponse getVaccinationPlanResponseFromVaccinationPlan(VaccinationPlan vaccinationPlan) {

        return new VaccinationPlanResponse(
                vaccinationPlan.getType(),
                vaccinationPlan.getDescription(),
                vaccinationPlan.getVaccinationDate(),
                vaccinationPlan.getDoctorId(),
                vaccinationPlan.getCreatedAt(),
                vaccinationPlan.getUpdatedAt()
        );
    }

    /**
     * Converts a VaccinationPlanRequest to a VaccinationPlan.
     *
     * @param vaccinationPlanRequest The vaccination plan request.
     * @return The converted VaccinationPlan.
     */
    private static VaccinationPlan getVaccinationPlanFromVaccinationPlanRequest(VaccinationPlanRequest vaccinationPlanRequest) {
        return new VaccinationPlan(
                vaccinationPlanRequest.type(),
                vaccinationPlanRequest.description(),
                vaccinationPlanRequest.vaccinationDate(),
                vaccinationPlanRequest.doctorId()
        );
    }
}
