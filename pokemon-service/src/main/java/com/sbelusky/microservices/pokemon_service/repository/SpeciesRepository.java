package com.sbelusky.microservices.pokemon_service.repository;

import com.sbelusky.microservices.pokemon_service.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for Species entity.
 * <p>
 * This interface extends {@link JpaRepository} and provides methods for CRUD operations on Species entity.
 */
public interface SpeciesRepository extends JpaRepository<Species, Long> {
}