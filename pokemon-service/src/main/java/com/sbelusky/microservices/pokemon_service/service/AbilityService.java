package com.sbelusky.microservices.pokemon_service.service;

import com.sbelusky.microservices.pokemon_service.dto.AbilityResponse;
import com.sbelusky.microservices.pokemon_service.model.Ability;
import com.sbelusky.microservices.pokemon_service.repository.AbilityRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for managing abilities.
 */
@Service
public class AbilityService {
    private final AbilityRepository abilityRepository;

    /**
     * Constructs a new AbilityService with the specified AbilityRepository.
     *
     * @param abilityRepository the repository to access ability data
     */
    public AbilityService(AbilityRepository abilityRepository) {
        this.abilityRepository = abilityRepository;
    }

    /**
     * Retrieves all abilities and converts them to AbilityResponse objects.
     *
     * @return a set of AbilityResponse objects representing all abilities
     */
    public Set<AbilityResponse> getAllAbilities() {
        return abilityRepository.findAll().stream()
                .map(AbilityService::getAbilityResponse)
                .collect(Collectors.toSet());
    }

    /**
     * Converts an Ability entity to an AbilityResponse.
     *
     * @param ability the Ability entity to convert
     * @return an AbilityResponse representing the specified Ability
     */
    private static AbilityResponse getAbilityResponse(Ability ability) {
        return new AbilityResponse(ability.getName());
    }
}
