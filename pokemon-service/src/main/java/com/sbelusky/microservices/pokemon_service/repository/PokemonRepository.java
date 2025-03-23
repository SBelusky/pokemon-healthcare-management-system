package com.sbelusky.microservices.pokemon_service.repository;

import com.sbelusky.microservices.pokemon_service.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for Pokemon entity.
 * <p>
 * This interface extends {@link JpaRepository} and provides methods for CRUD operations on Pokemon entity.
 */
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
