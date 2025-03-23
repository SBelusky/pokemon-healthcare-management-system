package com.sbelusky.microservices.treatment_service.repository;

import com.sbelusky.microservices.treatment_service.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for Medication entity.
 * <p>
 * This interface extends {@link JpaRepository} and provides methods for CRUD operations on Medication entity.
 */
public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
