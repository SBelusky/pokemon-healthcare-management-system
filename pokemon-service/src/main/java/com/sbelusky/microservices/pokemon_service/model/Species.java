package com.sbelusky.microservices.pokemon_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "weight")
    private String weight;

    @Column(name = "photo_image")
    private String photoImage;

    @Column(name = "base_happiness")
    private String baseHappiness;

    @Column(name = "capture_rate")
    private String captureRate;

    @Column(name = "color")
    private String color;

    @Column(name = "habitat")
    private String habitat;

    @Column(name = "is_mythical")
    private Boolean isMythical;

    @Column(name = "shape")
    private String shape;

    @Column(name = "growth_rate")
    private String growthRate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "species", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pokemon> pokemons;

    public Species() {
    }

    public Species(String name, String type, String weight, String photoImage, String baseHappiness, String captureRate, String color, String habitat, Boolean isMythical, String shape, String growthRate) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.photoImage = photoImage;
        this.baseHappiness = baseHappiness;
        this.captureRate = captureRate;
        this.color = color;
        this.habitat = habitat;
        this.isMythical = isMythical;
        this.shape = shape;
        this.growthRate = growthRate;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPhotoImage() {
        return photoImage;
    }

    public void setPhotoImage(String photoImage) {
        this.photoImage = photoImage;
    }

    public String getBaseHappiness() {
        return baseHappiness;
    }

    public void setBaseHappiness(String baseHappiness) {
        this.baseHappiness = baseHappiness;
    }

    public String getCaptureRate() {
        return captureRate;
    }

    public void setCaptureRate(String captureRate) {
        this.captureRate = captureRate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public Boolean getMythical() {
        return isMythical;
    }

    public void setMythical(Boolean mythical) {
        isMythical = mythical;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(String growthRate) {
        this.growthRate = growthRate;
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

    public List<Pokemon> getPokemons() {
        return pokemons;
    }
}
