package com.sbelusky.microservices.appointment_service.service;

import com.sbelusky.microservices.appointment_service.client.PokemonClient;
import com.sbelusky.microservices.appointment_service.client.UserClient;
import com.sbelusky.microservices.appointment_service.dto.AppointmentRequest;
import com.sbelusky.microservices.appointment_service.dto.AppointmentResponse;
import com.sbelusky.microservices.appointment_service.dto.PokemonResponse;
import com.sbelusky.microservices.appointment_service.dto.UserResponse;
import com.sbelusky.microservices.appointment_service.event.AppointmentCreatedEvent;
import com.sbelusky.microservices.appointment_service.exception.AppointmentNotFoundException;
import com.sbelusky.microservices.appointment_service.exception.PokemonNotFoundException;
import com.sbelusky.microservices.appointment_service.exception.UsertNotFoundException;
import com.sbelusky.microservices.appointment_service.model.Appointment;
import com.sbelusky.microservices.appointment_service.model.enums.AppointmentStatus;
import com.sbelusky.microservices.appointment_service.repository.AppointmentRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service for managing appointments.
 */
@Service
public class AppointmentService {
    private final PokemonClient pokemonClient;
    private final UserClient userClient;
    private final AppointmentRepository appointmentRepository;
    private final KafkaTemplate<String, AppointmentCreatedEvent> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(AppointmentService.class);

    public AppointmentService(PokemonClient pokemonClient, UserClient userClient, AppointmentRepository appointmentRepository, KafkaTemplate<String, AppointmentCreatedEvent> kafkaTemplate) {
        this.pokemonClient = pokemonClient;
        this.userClient = userClient;
        this.appointmentRepository = appointmentRepository;
        this.kafkaTemplate = kafkaTemplate;
    }


    /**
     * Creates a new appointment and saves it to the database.
     *
     * @param appointmentRequest contains information about the appointment to be created
     * @return AppointmentResponse containing information about the created appointment
     * @throws PokemonNotFoundException if the Pokemon with the provided id does not exist
     * @throws UsertNotFoundException    if the User with the provided id does not exist
     */
    public AppointmentResponse createAppointment(@Valid AppointmentRequest appointmentRequest) throws PokemonNotFoundException, UsertNotFoundException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentRequest.appointmentDate());
        appointment.setStatus(AppointmentStatus.SCHEDULED.name());
        appointment.setCreatedAt(LocalDateTime.now());

        PokemonResponse pokemonResponse = pokemonClient.getPokemonById(appointmentRequest.pokemonId());
        UserResponse userResponse = userClient.getUserById(appointmentRequest.doctorId());

        if(pokemonResponse == null){
            throw new PokemonNotFoundException("Pokemon not found with id: " + appointmentRequest.pokemonId());
        }else if(userResponse == null){
            throw new UsertNotFoundException("User not found with id: " + appointmentRequest.doctorId());
        }else{
            appointment.setPokemonId(appointmentRequest.pokemonId());
            appointment.setDoctorId(appointmentRequest.doctorId());
        }

        appointmentRepository.save(appointment);

        // send message to Kafka Topic
        if(userResponse.email() != null) {
            AppointmentCreatedEvent appointmentCreatedEvent = new AppointmentCreatedEvent();

            appointmentCreatedEvent.setPokemonName(pokemonResponse.name());
            appointmentCreatedEvent.setOwnerEmail(userResponse.email());
            appointmentCreatedEvent.setAppointmentDate(userResponse.email());
            appointmentCreatedEvent.setOwnerId(2L);

            if (appointmentCreatedEvent == null) {
                throw new IllegalArgumentException("appointmentCreatedEvent is null!");
            } else {
                log.info("Start - sending message to Kafka Topic appointment-created: {}", appointmentCreatedEvent);
                try {
                    kafkaTemplate.send("appointment-created", appointmentCreatedEvent);
                } catch (Exception e) {
                    System.out.println("Error sending message to Kafka Topic appointment-created: " + e.getMessage());
                }
                log.info("End - sending message to Kafka Topic appointment-created: {}", appointmentCreatedEvent);
            }
        }

        return getAppointmentResponse(appointment);
    }

    /**
     * Retrieves an appointment from the database.
     *
     * @param id id of the appointment to be retrieved
     * @return AppointmentResponse containing information about the retrieved appointment
     * @throws AppointmentNotFoundException if the appointment with the provided id does not exist
     */
    public AppointmentResponse getAppointmentById(Long id) throws AppointmentNotFoundException {
        Appointment appointment = appointmentRepository
                .findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + id));

        return getAppointmentResponse(appointment);
    }

    /**
     * Retrieves all appointments for a given Pokemon.
     *
     * @param pokemonId id of the Pokemon
     * @return Set of AppointmentResponse containing information about the retrieved appointments
     * @throws PokemonNotFoundException if the Pokemon with the provided id does not exist
     */
    public Set<AppointmentResponse> getAppointmentByPokemonId(Long pokemonId) throws PokemonNotFoundException {
        PokemonResponse pokemonResponse = pokemonClient.getPokemonById(pokemonId);

        if(pokemonResponse == null){
            throw new PokemonNotFoundException("Pokemon not found with id: " + pokemonId);
        }

        Example<Appointment> example = Example.of(new Appointment());
        example.getProbe().setPokemonId(pokemonId);

     return appointmentRepository.findAll(example).stream()
             .filter(appointment -> !appointment.getStatus().equals(AppointmentStatus.CANCELLED.name()))
             .sorted(Comparator.comparing(Appointment::getAppointmentDate).reversed())
             .map(AppointmentService::getAppointmentResponse)
             .collect(Collectors.toSet());
    }

    /**
     * Retrieves all appointments for a given doctor.
     *
     * @param doctorId id of the doctor
     * @return Set of AppointmentResponse containing information about the retrieved appointments
     * @throws PokemonNotFoundException if the Pokemon with the provided id does not exist
     */
    public Set<AppointmentResponse> getAppointmentByDoctorId(Long doctorId) throws PokemonNotFoundException {
        UserResponse userResponse = userClient.getUserById(doctorId);

        if(userResponse == null){
            throw new PokemonNotFoundException("Pokemon not found with id: " + doctorId);
        }

        Example<Appointment> example = Example.of(new Appointment());
        example.getProbe().setDoctorId(doctorId);

        return appointmentRepository.findAll(example).stream()
                .filter(appointment -> !appointment.getStatus().equals(AppointmentStatus.CANCELLED.name()))
                .sorted(Comparator.comparing(Appointment::getAppointmentDate).reversed())
                .map(AppointmentService::getAppointmentResponse)
                .collect(Collectors.toSet());
    }

    /**
     * Updates the status of an appointment.
     *
     * @param appointmentRequest contains information about the appointment to be updated
     * @return AppointmentResponse containing information about the updated appointment
     * @throws AppointmentNotFoundException if the appointment with the provided id does not exist
     */
    public AppointmentResponse updateAppointmentStatusById(@Valid AppointmentRequest appointmentRequest) throws AppointmentNotFoundException {
        Appointment appointment = appointmentRepository
                .findById(appointmentRequest.id())
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + appointmentRequest.id()));

        appointment.setStatus(appointmentRequest.status());
        appointment.setUpdatedAt(LocalDateTime.now());

        appointmentRepository.save(appointment);

        return getAppointmentResponse(appointment);
    }

    /**
     * Deletes an appointment from the database.
     *
     * @param id id of the appointment to be deleted
     * @throws AppointmentNotFoundException if the appointment with the provided id does not exist
     */
    public void deleteAppointmentById(Long id) throws AppointmentNotFoundException {
        Appointment appointment = appointmentRepository
                .findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + id));

        appointment.setStatus(AppointmentStatus.CANCELLED.name());
        appointment.setUpdatedAt(LocalDateTime.now());

        appointmentRepository.save(appointment);
    }

    /**
     * Converts an Appointment to an AppointmentResponse.
     *
     * @param appointment the appointment to be converted
     * @return AppointmentResponse containing information about the appointment
     */
    private static AppointmentResponse getAppointmentResponse(Appointment appointment) {
        return new AppointmentResponse(
                appointment.getAppointmentDate(),
                appointment.getStatus(),
                appointment.getPokemonId(),
                appointment.getDoctorId(),
                appointment.getCreatedAt(),
                appointment.getUpdatedAt()
        );
    }
}
