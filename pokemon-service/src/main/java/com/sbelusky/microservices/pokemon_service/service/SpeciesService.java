package com.sbelusky.microservices.pokemon_service.service;

import com.sbelusky.microservices.pokemon_service.dto.SpeciesResponse;
import com.sbelusky.microservices.pokemon_service.model.Species;
import com.sbelusky.microservices.pokemon_service.repository.SpeciesRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SpeciesService {
    private final SpeciesRepository speciesRepository;

    /**
     * Constructs a SpeciesService with the specified SpeciesRepository.
     *
     * @param speciesRepository repository for accessing species data from the database
     */
    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    /**
     * Retrieves all species from the repository and converts them to SpeciesResponse objects.
     *
     * @return a set of SpeciesResponse containing information about all species
     */
    public Set<SpeciesResponse> getAllSpecies() {
        return speciesRepository.findAll().stream()
                .map(SpeciesService::getSpeciesResponse)
                .collect(Collectors.toSet());
    }

    /**
     * Converts a Species object to a SpeciesResponse object.
     *
     * @param species the Species object to be converted
     * @return a SpeciesResponse containing the species' data
     */
    private static SpeciesResponse getSpeciesResponse(Species species) {
        return new SpeciesResponse(
                species.getId(),
                species.getName(),
                species.getType(),
                species.getWeight(),
                species.getPhotoImage(),
                species.getBaseHappiness(),
                species.getCaptureRate(),
                species.getColor(),
                species.getHabitat(),
                species.getMythical(),
                species.getShape(),
                species.getGrowthRate()
        );
    }
}
