package com.sbelusky.microservices.appointment_service.repository;

import com.sbelusky.microservices.appointment_service.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA repository for managing appointments.
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    /**
     * Finds all appointments by the given doctor ID.
     *
     * @param doctorId doctor ID
     * @return list of appointments
     */
    List<Appointment> findByDoctorId(Long doctorId);

    /**
     * Finds all appointments by the given pokemon ID.
     *
     * @param pokemonId pokemon ID
     * @return list of appointments
     */
    List<Appointment> findByPokemonId(Long pokemonId);
}
