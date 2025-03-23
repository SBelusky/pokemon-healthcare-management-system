package com.sbelusky.microservices.treatment_service.service;

import com.sbelusky.microservices.treatment_service.client.PokemonClient;
import com.sbelusky.microservices.treatment_service.client.UserClient;
import com.sbelusky.microservices.treatment_service.dto.PokemonResponse;
import com.sbelusky.microservices.treatment_service.dto.SurgeryRequest;
import com.sbelusky.microservices.treatment_service.dto.SurgeryResponse;
import com.sbelusky.microservices.treatment_service.dto.UserResponse;
import com.sbelusky.microservices.treatment_service.exception.PokemonNotFoundException;
import com.sbelusky.microservices.treatment_service.exception.TreatmentHistoryNotFound;
import com.sbelusky.microservices.treatment_service.exception.UserNotFoundException;
import com.sbelusky.microservices.treatment_service.model.Surgery;
import com.sbelusky.microservices.treatment_service.model.TreatmentHistory;
import com.sbelusky.microservices.treatment_service.repository.SurgeryRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service for managing surgeries.
 */
@Service
public class SurgeryService {
    private final SurgeryRepository surgeryRepository;
    private final UserClient userClient;
    private final PokemonClient pokemonClient;
    private final TreatmentHistoryService historyService;

    /**
     * Constructor for SurgeryService.
     *
     * @param surgeryRepository repository for surgeries
     * @param userClient        client for users
     * @param pokemonClient     client for pokemons
     * @param historyService    service for treatment histories
     */
    public SurgeryService(SurgeryRepository surgeryRepository, UserClient userClient, PokemonClient pokemonClient,TreatmentHistoryService historyService) {
        this.surgeryRepository = surgeryRepository;
        this.userClient = userClient;
        this.pokemonClient = pokemonClient;
        this.historyService = historyService;
    }

    /**
     * Creates a new surgery based on the given request.
     *
     * @param surgeryRequest the request containing the surgery details
     * @return the created surgery
     * @throws UserNotFoundException if the user with the given ID does not exist
     * @throws PokemonNotFoundException if the pokemon with the given ID does not exist
     */
    public SurgeryResponse createSurgery(@Valid SurgeryRequest surgeryRequest) throws UserNotFoundException, PokemonNotFoundException {
        UserResponse userResponse = userClient.getUserById(surgeryRequest.doctorId());
        PokemonResponse pokemonResponse = pokemonClient.getPokemonById(surgeryRequest.pokemonId());

        if(userResponse == null) {
            throw new UserNotFoundException("User not found with id: " + surgeryRequest.doctorId());
        }else if(pokemonResponse == null){
            throw new PokemonNotFoundException("Pokemon not found with id:" + surgeryRequest.pokemonId());
        }

        Surgery surgery = getSurgeryFromSurgeryRequest(surgeryRequest);

        if(!historyService.hasPokemonTreatmentHistory(surgeryRequest.pokemonId())){
            TreatmentHistory treatmentHistory = historyService.createTreatmentHistory(surgeryRequest.pokemonId());
            surgery.setTreatmentHistory(treatmentHistory);
        }else{
            surgery.setTreatmentHistory(historyService.getTreatmentHistory(surgeryRequest.pokemonId()));
        }

        return getSurgeryResponseFromSurgery(surgeryRepository.save(surgery));
    }

    /**
     * Returns all surgeries associated with the given pokemon ID.
     *
     * @param pokemonId the ID of the pokemon
     * @return a set of surgeries associated with the given pokemon ID
     * @throws PokemonNotFoundException if the pokemon with the given ID does not exist
     * @throws TreatmentHistoryNotFound if the treatment history for the given pokemon ID does not exist
     */
    public Set<SurgeryResponse> getSurgeryByPokemonId(Long pokemonId) throws PokemonNotFoundException, TreatmentHistoryNotFound {
        PokemonResponse pokemonResponse = pokemonClient.getPokemonById(pokemonId);

        if(pokemonResponse == null){
            throw new PokemonNotFoundException("Pokemon not found with id:" + pokemonId);
        }
        return historyService.getTreatmentHistoryByPokemonId(pokemonId).surgeries();
    }

    /**
     * Returns all surgeries associated with the given doctor ID.
     *
     * @param doctorId the ID of the doctor
     * @return a set of surgeries associated with the given doctor ID
     * @throws UserNotFoundException if the user with the given ID does not exist
     */
    public Set<SurgeryResponse> getSurgeryByDoctorId(Long doctorId) throws UserNotFoundException {
        UserResponse userResponse = userClient.getUserById(doctorId);

        if(userResponse == null) {
            throw new UserNotFoundException("User not found with id: " + doctorId);
        }

        Example<Surgery> example = Example.of(new Surgery());
        example.getProbe().setDoctorId(doctorId);

        return surgeryRepository.findAll(example).stream()
                .sorted(Comparator.comparing(Surgery::getSurgeryDate).reversed())
                .map(SurgeryService::getSurgeryResponseFromSurgery)
                .collect(Collectors.toSet());
    }

    /**
     * Creates a Surgery object from a SurgeryRequest.
     *
     * @param surgeryRequest the request containing the surgery details
     * @return the created Surgery object
     */
    public static Surgery getSurgeryFromSurgeryRequest(SurgeryRequest surgeryRequest) {
        return new Surgery(
                surgeryRequest.description(),
                surgeryRequest.surgeryDate(),
                surgeryRequest.doctorId()
        );
    }

    /**
     * Creates a SurgeryResponse object from a Surgery object.
     * @param surgery the surgery object
     * @return the created SurgeryResponse object
     */
    public static SurgeryResponse getSurgeryResponseFromSurgery(Surgery surgery) {
        return new SurgeryResponse(
                surgery.getDescription(),
                surgery.getSurgeryDate(),
                surgery.getDoctorId(),
                surgery.getTreatmentHistory().getPokemonId(),
                surgery.getCreatedAt(),
                surgery.getUpdatedAt()
        );
    }
}
