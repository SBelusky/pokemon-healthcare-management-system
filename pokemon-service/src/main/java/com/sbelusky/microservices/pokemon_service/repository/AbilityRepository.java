package com.sbelusky.microservices.pokemon_service.repository;

import com.sbelusky.microservices.pokemon_service.model.Ability;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for Ability entity.
 * <p>
 * This interface extends {@link JpaRepository} and provides methods for CRUD operations on Ability entity.
 */
public interface AbilityRepository extends JpaRepository<Ability, Long> {
}
