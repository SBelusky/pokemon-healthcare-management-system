package com.sbelusky.microservices.treatment_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_surgeries")
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "surgery_date")
    private LocalDateTime surgeryDate;

    @Column(name = "doctorId")
    private Long doctorId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "treatment_history_id")
    private TreatmentHistory treatmentHistory;

    public TreatmentHistory getTreatmentHistory() {
        return treatmentHistory;
    }

    public void setTreatmentHistory(TreatmentHistory treatmentHistory) {
        this.treatmentHistory = treatmentHistory;
        this.createdAt = LocalDateTime.now();
    }

    public Surgery(String description, LocalDateTime surgeryDate, Long doctorId) {
        this.description = description;
        this.surgeryDate = surgeryDate;
        this.doctorId = doctorId;
        this.createdAt = LocalDateTime.now();
    }

    public Surgery() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getSurgeryDate() {
        return surgeryDate;
    }

    public void setSurgeryDate(LocalDateTime surgeryDate) {
        this.surgeryDate = surgeryDate;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
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
}
