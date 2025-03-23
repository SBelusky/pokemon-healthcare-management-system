package com.sbelusky.microservices.user_service.repository;

import com.sbelusky.microservices.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring Data JPA repository for User entity.
 * <p>
 * This interface extends {@link JpaRepository} and provides methods for CRUD operations on User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

