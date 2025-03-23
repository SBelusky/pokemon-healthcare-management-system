package com.sbelusky.microservices.treatment_service.repository;

import com.sbelusky.microservices.treatment_service.model.VaccinationPlan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for VaccinationPlan entity.
 * <p>
 * This interface extends {@link JpaRepository} and provides methods for CRUD operations on VaccinationPlan entity.
 */
public interface VaccinationPlanRepository extends JpaRepository<VaccinationPlan, Long> {
}
