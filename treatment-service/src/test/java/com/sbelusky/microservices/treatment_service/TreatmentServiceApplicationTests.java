package com.sbelusky.microservices.treatment_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@Import(TestContainerConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class TreatmentServiceApplicationTests {
    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

//    @Test
//    void shouldGetTreatmentHistoryByPokemonId() {
//        Long pokemonId = 1L;
//
//        given()
//            .contentType("application/json")
//        .when()
//            .get("/api/treatment-history/pokemon/" + pokemonId)
//        .then()
//            .statusCode(HttpStatus.OK.value())
//            .body("size()", greaterThan(1));
//    }
//
//    @Test
//    void shouldGetAppointmentByOwnerId() {
//        Long ownerId = 2L;
//
//        given()
//                .contentType("application/json")
//                .when()
//                .get("/api/appointments/user/" + ownerId)
//                .then()
//                .statusCode(HttpStatus.OK.value())
//                .body("size()", greaterThan(0));
//    }
}
