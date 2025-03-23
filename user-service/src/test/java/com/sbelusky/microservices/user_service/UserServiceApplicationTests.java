package com.sbelusky.microservices.user_service;

import com.sbelusky.microservices.user_service.dto.UserRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Import(TestContainerConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceApplicationTests {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldCreateUser(){
        UserRequest userRequest = new UserRequest(
            "jdeer",
            "john.deer@phms.com",
            "John",
            "Deer",
                "doctor"
        );

        given()
            .contentType("application/json")
            .body(userRequest)
        .when()
            .post("/api/users")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("userName", equalTo("jdeer"))
            .body("role", equalTo("doctor"));
    }

    @Test
    void shouldGetUserByEmail(){
        String email = "samuel.belusky@gmail.com";

        given()
            .contentType("application/json")
        .when()
            .get("/api/users?email=" + email)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("userName", equalTo("sbelusky"))
            .body("role", equalTo("trainer"));
    }

    @Test
    void shouldGetUserById(){
        Long id = 2L;

        given()
            .contentType("application/json")
        .when()
            .get("/api/users/" + id)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("userName", equalTo("sbelusky"))
            .body("role", equalTo("trainer"));
    }
}
