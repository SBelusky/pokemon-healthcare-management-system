package com.sbelusky.microservices.pokemon_center_service.repository;

import com.sbelusky.microservices.pokemon_center_service.model.PokemonCenter;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This interface is a MongoDB data access object (DAO) for PokemonCenter objects.
 * It provides a set of methods for performing CRUD operations on the PokemonCenter collection.
 */
public interface PokemonCenterRepository extends MongoRepository<PokemonCenter, Long> {

}