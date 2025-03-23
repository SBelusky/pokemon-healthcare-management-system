package com.sbelusky.microservices.pokemon_service;

import com.sbelusky.microservices.pokemon_service.stub.UserClientStub;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Import(TestContainerConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class PokemonServiceApplicationTests {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldCreatePokemon() throws JsonProcessingException{
        String requestBody = """
                {
                  "id": 0,
                  "name": "My New Pokemon",
                  "birthDate": "2025-03-07",
                  "speciesId": 1,
                  "abilities": [
                    {
                      "id": 1
                    }
                  ],
                  "ownerId": 2 
                }""";

        UserClientStub.stubUserCall("2");

        given()
            .contentType("application/json")
            .body(requestBody)
        .when()
            .post("/api/pokemons")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("name", equalTo("My New Pokemon"))
            .body("birthDate", equalTo("2025-03-07"));
    }

    @Test
    void shouldGetPokemonById(){
        given()
            .contentType("application/json")
        .when()
            .get("/api/pokemons/1")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("name", equalTo("Bulby"))
            .body("birthDate", equalTo("2023-01-01"));
    }

    @Test
    void shouldGetAllAbilities(){
        given()
            .contentType("application/json")
        .when()
            .get("/api/abilities")
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldGetAllSpecies() {
        given()
            .contentType("application/json")
        .when()
            .get("/api/species")
        .then()
            .statusCode(HttpStatus.OK.value());
    }
}