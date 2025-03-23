package com.sbelusky.microservices.treatment_service.repository;

import com.sbelusky.microservices.treatment_service.model.TreatmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for TreatmentHistory entity.
 * <p>
 * This interface extends {@link JpaRepository} and provides methods for CRUD operations on TreatmentHistory entity.
 */
public interface TreatmentHistoryRepository extends JpaRepository<TreatmentHistory, Long> {
}
