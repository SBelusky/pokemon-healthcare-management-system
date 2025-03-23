package com.sbelusky.microservices.pokemon_center_service.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Document(value="t_pokemon_centers")
public class PokemonCenter {

    @Id
    private int id;

    @Field(name = "title")
    private String name;

    @Field(name = "city")
    private String city;

    @Field(name = "street")
    private String street;

    @Field(name = "country")
    private String country;

    @Field(name = "phone_number")
    private String phoneNumber;

    @Field(name = "email")
    private String email;

    @Field(name = "specialization")
    private String specialization;

    @Field(name = "created_at")
    private LocalDateTime createdAt;

    @Field(name = "updated_at")
    private LocalDateTime updatedAt;

    public PokemonCenter() {
    }

    public PokemonCenter(String name, String city, String street, String country, String phoneNumber, String email, String specialization) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.specialization = specialization;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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
