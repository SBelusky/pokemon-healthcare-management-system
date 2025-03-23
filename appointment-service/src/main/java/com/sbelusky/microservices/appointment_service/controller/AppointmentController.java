package com.sbelusky.microservices.appointment_service.controller;

import com.sbelusky.microservices.appointment_service.dto.AppointmentRequest;
import com.sbelusky.microservices.appointment_service.dto.AppointmentResponse;
import com.sbelusky.microservices.appointment_service.exception.AppointmentNotFoundException;
import com.sbelusky.microservices.appointment_service.exception.PokemonNotFoundException;
import com.sbelusky.microservices.appointment_service.exception.UsertNotFoundException;
import com.sbelusky.microservices.appointment_service.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * REST API controller for managing appointments.
 */
@RestController
@RequestMapping("/api/appointments")
@Tag(name = "Appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * Creates a new appointment for the given pokemon and doctor.
     *
     * @param appointmentRequest the request containing the appointment details
     * @return the created appointment
     * @throws PokemonNotFoundException if the pokemon does not exist
     * @throws UsertNotFoundException   if the doctor does not exist
     */
    @Operation(summary = "Create an appointment")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponse createAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest) throws PokemonNotFoundException, UsertNotFoundException {
        return appointmentService.createAppointment(appointmentRequest);
    }

    /**
     * Retrieves an appointment by its ID.
     *
     * @param id the ID of the appointment
     * @return the appointment
     * @throws AppointmentNotFoundException if the appointment does not exist
     */
    @Operation(summary = "Get an appointment by its ID")
    @Parameter(name = "id", description = "The ID of the appointment")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse getAppointmentById(@PathVariable("id") Long id) throws AppointmentNotFoundException {
        return appointmentService.getAppointmentById(id);
    }

    /**
     * Retrieves all appointments for the given pokemon.
     *
     * @param pokemonId the ID of the pokemon
     * @return the appointments
     * @throws PokemonNotFoundException if the pokemon does not exist
     */
    @Operation(summary = "Get all appointments for a pokemon")
    @Parameter(name = "pokemonId", description = "The ID of the pokemon")
    @GetMapping("/pokemon/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set<AppointmentResponse> getAppointmentByPokemonId(@PathVariable("id") Long pokemonId) throws PokemonNotFoundException {
        return appointmentService.getAppointmentByPokemonId(pokemonId);
    }

    /**
     * Retrieves all appointments for the given doctor.
     *
     * @param doctorId the ID of the doctor
     * @return the appointments
     * @throws PokemonNotFoundException if the doctor does not exist
     */
    @Operation(summary = "Get all appointments for a doctor")
    @Parameter(name = "doctorId", description = "The ID of the doctor")
    @GetMapping("/doctor/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set<AppointmentResponse> getAppointmentByDoctorId(@PathVariable("id") Long doctorId) throws PokemonNotFoundException {
        return appointmentService.getAppointmentByDoctorId(doctorId);
    }

    /**
     * Deletes an appointment by its ID.
     *
     * @param id the ID of the appointment
     * @throws AppointmentNotFoundException if the appointment does not exist
     */
    @Operation(summary = "Delete an appointment by its ID")
    @Parameter(name = "id", description = "The ID of the appointment")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointmentById(@PathVariable("id") Long id) throws AppointmentNotFoundException {
        appointmentService.deleteAppointmentById(id);
    }

    /**
     * Updates the status of an appointment by its ID.
     *
     * @param appointmentRequest the request containing the updated status
     * @return the updated appointment
     * @throws AppointmentNotFoundException if the appointment does not exist
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse updateAppointmentStatusById(@Valid @RequestBody AppointmentRequest appointmentRequest) throws AppointmentNotFoundException {
        return appointmentService.updateAppointmentStatusById(appointmentRequest);
    }
}