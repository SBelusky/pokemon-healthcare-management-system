package com.sbelusky.microservices.treatment_service.controller;

import com.sbelusky.microservices.treatment_service.dto.VaccinationPlanRequest;
import com.sbelusky.microservices.treatment_service.dto.VaccinationPlanResponse;
import com.sbelusky.microservices.treatment_service.exception.PokemonNotFoundException;
import com.sbelusky.microservices.treatment_service.exception.TreatmentHistoryNotFound;
import com.sbelusky.microservices.treatment_service.exception.UserNotFoundException;
import com.sbelusky.microservices.treatment_service.service.VaccinationPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * REST controller for managing vaccination plans.
 */
@RestController
@RequestMapping("/api/vaccination-plans")
public class VaccinationPlanController {
    private final VaccinationPlanService vaccinationPlanService;

    /**
     * Constructor.
     *
     * @param vaccinationPlanService the service for vaccination plans
     */
    public VaccinationPlanController(VaccinationPlanService vaccinationPlanService) {
        this.vaccinationPlanService = vaccinationPlanService;
    }

    /**
     * Creates a vaccination plan.
     *
     * @param vaccinationPlanRequest the vaccination plan to create
     * @return the created vaccination plan
     * @throws UserNotFoundException   if the doctor is not found
     * @throws PokemonNotFoundException if the pokemon is not found
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create vaccination plan", description = "Creates a vaccination plan")
    @ApiResponse(responseCode = "201", description = "Vaccination plan created")
    public VaccinationPlanResponse createVaccinationPlan(
            @Valid @RequestBody VaccinationPlanRequest vaccinationPlanRequest) throws UserNotFoundException, PokemonNotFoundException {
        return vaccinationPlanService.createVaccinationPlan(vaccinationPlanRequest);
    }

    /**
     * Gets the vaccination plans for a pokemon.
     *
     * @param pokemonId the id of the pokemon
     * @return the vaccination plans for the pokemon
     * @throws PokemonNotFoundException   if the pokemon is not found
     * @throws TreatmentHistoryNotFound if the treatment history is not found
     */
    @GetMapping("/pokemon/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get vaccination plans by pokemon id", description = "Gets the vaccination plans for a pokemon")
    @ApiResponse(responseCode = "200", description = "Vaccination plans for pokemon")
    public Set<VaccinationPlanResponse> getVaccinationPlansByPokemonId(
            @Parameter(name = "id", description = "The id of the pokemon") @PathVariable("id") Long pokemonId) throws PokemonNotFoundException, TreatmentHistoryNotFound {
        return vaccinationPlanService.getVaccinationPlansByPokemonId(pokemonId);
    }

    /**
     * Gets the vaccination plans for a doctor.
     *
     * @param doctorId the id of the doctor
     * @return the vaccination plans for the doctor
     * @throws UserNotFoundException if the doctor is not found
     */
    @GetMapping("/doctor/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get vaccination plans by doctor id", description = "Gets the vaccination plans for a doctor")
    @ApiResponse(responseCode = "200", description = "Vaccination plans for doctor")
    public Set<VaccinationPlanResponse> getVaccinationPlansByDoctorId(
            @Parameter(name = "id", description = "The id of the doctor") @PathVariable("id") Long doctorId) throws UserNotFoundException {
        return vaccinationPlanService.getVaccinationPlansByDoctorId(doctorId);
    }
}
