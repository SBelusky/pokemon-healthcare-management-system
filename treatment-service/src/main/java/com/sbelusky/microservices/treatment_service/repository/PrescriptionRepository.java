package com.sbelusky.microservices.treatment_service.repository;

import com.sbelusky.microservices.treatment_service.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for Prescription entity.
 * <p>
 * This interface extends {@link JpaRepository} and provides methods for CRUD operations on Prescription entity.
 */
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}
