package com.sbelusky.microservices.appointment_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sbelusky.microservices.appointment_service.stub.PokemonClientStub;
import com.sbelusky.microservices.appointment_service.stub.UserClientStub;
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
class AppointmentServiceApplicationTests {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldCreateAppointment() throws JsonProcessingException {
        String requestBody = """
                {
                   "id": 0,
                   "appointmentDate": "2025-03-07T12:26:42.495Z",
                   "status": "SCHEDULED",
                   "pokemonId": 2,
                   "doctorId": 2
                 }""";

        UserClientStub.stubUserCall("2");
        PokemonClientStub.stubPokemonCall("2");

        given()
            .contentType("application/json")
            .body(requestBody)
        .when()
            .post("/api/appointments")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("status", equalTo("SCHEDULED"));
    }

	@Test
	void shouldGetAppointmentById() {
        String appointmentId = "1";

        given()
            .contentType("application/json")
        .when()
            .get("/api/appointments/" + appointmentId)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("status", equalTo("SCHEDULED"))
            .body("pokemonId", equalTo(2))
            .body("doctorId", equalTo(2));
	}

    @Test
    void shouldGetAppointmentByPokemonId() {
        Long pokemonId = 2L;

        given()
            .contentType("application/json")
        .when()
            .get("/api/appointments/pokemon/" + pokemonId)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("size()", greaterThan(0));
    }

    @Test
    void shouldGetAppointmentByDoctorId() {
        Long doctorId = 2L;

        given()
            .contentType("application/json")
        .when()
            .get("/api/appointments/doctor/" + doctorId)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("size()", greaterThan(0));
    }

    @Test
    void shouldUpdateAppointmentStatusById() {
        String requestBody = """
                {
                   "id": 1,
                   "appointmentDate": "2025-03-07T12:26:42.495Z",
                   "status": "SCHEDULED",
                   "pokemonId": 2,
                   "doctorId": 2
                 }""";

        given()
            .contentType("application/json")
            .body(requestBody)
        .when()
            .put("/api/appointments")
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldDeleteAppointmentById() {
        Long appointmentId = 1L;

        given()
            .contentType("application/json")
        .when()
            .delete("/api/appointments/" + appointmentId)
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
