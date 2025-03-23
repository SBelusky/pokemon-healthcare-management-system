package com.sbelusky.microservices.pokemon_service.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "t_pokemons")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "t_species_id", nullable = false)
    private Species species;

    @ManyToMany
    @JoinTable(name = "t_pokemons_has_abilities", joinColumns = @JoinColumn(name = "t_pokemon_id"), inverseJoinColumns = @JoinColumn(name = "t_ability_id"))
    private Set<Ability> abilities;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    public Pokemon() {
    }

    public Pokemon(String name, LocalDate birthDate, Species species, Set<Ability> abilities, Long ownerId) {
        this.name = name;
        this.birthDate = birthDate;
        this.species = species;
        this.abilities = abilities;
        this.ownerId = ownerId;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

    public Species getSpecies() {
        return species;
    }

    public Set<Ability> getAbilities() {
        return abilities;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public void setAbilities(Set<Ability> abilities) {
        this.abilities = abilities;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
