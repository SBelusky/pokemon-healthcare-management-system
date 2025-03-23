package com.sbelusky.microservices.treatment_service.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_treatment_history")
public class TreatmentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pokemon_id")
    private Long pokemonId;

    @OneToMany(mappedBy = "treatmentHistory")
    List<Surgery> surgeries;

    @OneToMany(mappedBy = "treatmentHistory")
    List<Prescription> prescriptions;

    @OneToMany(mappedBy = "treatmentHistory")
    List<VaccinationPlan> vaccinationPlans;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public TreatmentHistory(Long pokemonId) {
        this.pokemonId = pokemonId;
        this.createdAt = LocalDateTime.now();
    }

    public TreatmentHistory() {
    }

    public Long getId() {
        return id;
    }

    public Long getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(Long pokemonId) {
        this.pokemonId = pokemonId;
    }

    public List<Surgery> getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(List<Surgery> surgeries) {
        this.surgeries = surgeries;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<VaccinationPlan> getVaccinationPlans() {
        return vaccinationPlans;
    }

    public void setVaccinationPlans(List<VaccinationPlan> vaccinationPlans) {
        this.vaccinationPlans = vaccinationPlans;
    }
}
