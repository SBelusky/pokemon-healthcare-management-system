package com.sbelusky.microservices.treatment_service.repository;

import com.sbelusky.microservices.treatment_service.model.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for Surgery entity.
 * <p>
 * This interface extends {@link JpaRepository} and provides methods for CRUD operations on Surgery entity.
 */
public interface SurgeryRepository extends JpaRepository<Surgery, Long> {
}
