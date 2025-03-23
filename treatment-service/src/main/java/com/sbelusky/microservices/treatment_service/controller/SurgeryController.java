package com.sbelusky.microservices.treatment_service.controller;

import com.sbelusky.microservices.treatment_service.dto.SurgeryRequest;
import com.sbelusky.microservices.treatment_service.dto.SurgeryResponse;
import com.sbelusky.microservices.treatment_service.exception.PokemonNotFoundException;
import com.sbelusky.microservices.treatment_service.exception.TreatmentHistoryNotFound;
import com.sbelusky.microservices.treatment_service.exception.UserNotFoundException;
import com.sbelusky.microservices.treatment_service.service.SurgeryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * REST controller for managing surgeries.
 */
@RestController
@RequestMapping("/api/surgeries")
public class SurgeryController {
    private final SurgeryService surgeryService;

    public SurgeryController(SurgeryService surgeryService) {
        this.surgeryService = surgeryService;
    }

    /**
     * Creates a new surgery based on the given request.
     *
     * @param surgeryRequest the request containing the surgery details
     * @return the created surgery
     * @throws UserNotFoundException if the user with the given ID does not exist
     * @throws PokemonNotFoundException if the pokemon with the given ID does not exist
     * @see SurgeryService#createSurgery(SurgeryRequest)
     */
    @PostMapping
    @Operation(summary = "Create a new surgery", description = "Creates a new surgery based on the given request")
    @ApiResponse(responseCode = "201", description = "The created surgery")
    @ResponseStatus(HttpStatus.CREATED)
    public SurgeryResponse createSurgery(@Valid @RequestBody SurgeryRequest surgeryRequest) throws UserNotFoundException, PokemonNotFoundException {
        return surgeryService.createSurgery(surgeryRequest);
    }

    /**
     * Returns all surgeries associated with the given pokemon ID.
     *
     * @param pokemonId the ID of the pokemon
     * @return a set of surgeries associated with the given pokemon ID
     * @throws PokemonNotFoundException if the pokemon with the given ID does not exist
     * @throws TreatmentHistoryNotFound if the treatment history for the given pokemon ID does not exist
     * @see SurgeryService#getSurgeryByPokemonId(Long)
     */
    @GetMapping("/pokemon/{id}")
    @Operation(summary = "Get all surgeries associated with the given pokemon ID", description = "Returns all surgeries associated with the given pokemon ID")
    @ApiResponse(responseCode = "200", description = "A set of surgeries associated with the given pokemon ID")
    @ResponseStatus(HttpStatus.OK)
    public Set<SurgeryResponse> getSurgeryByPokemonId(@Parameter(description = "the ID of the pokemon") @PathVariable("id") Long pokemonId) throws PokemonNotFoundException, TreatmentHistoryNotFound {
        return surgeryService.getSurgeryByPokemonId(pokemonId);
    }

    /**
     * Returns all surgeries associated with the given doctor ID.
     *
     * @param doctorId the ID of the doctor
     * @return a set of surgeries associated with the given doctor ID
     * @throws UserNotFoundException if the user with the given ID does not exist
     * @see SurgeryService#getSurgeryByDoctorId(Long)
     */
    @GetMapping("/doctor/{id}")
    @Operation(summary = "Get all surgeries associated with the given doctor ID", description = "Returns all surgeries associated with the given doctor ID")
    @ApiResponse(responseCode = "200", description = "A set of surgeries associated with the given doctor ID")
    @ResponseStatus(HttpStatus.OK)
    public Set<SurgeryResponse> getSurgeryByDoctorId(@Parameter(description = "the ID of the doctor") @PathVariable("id") Long doctorId) throws UserNotFoundException {
        return surgeryService.getSurgeryByDoctorId(doctorId);
    }
}
