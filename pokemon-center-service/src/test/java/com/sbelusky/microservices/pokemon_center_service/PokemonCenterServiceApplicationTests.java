package com.sbelusky.microservices.pokemon_center_service;

import com.sbelusky.microservices.pokemon_center_service.dto.PokemonCenterResponse;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import wiremock.org.eclipse.jetty.http.HttpMethod;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PokemonCenterServiceApplicationTests {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldCreatePokemonCenter() {
        String requestBody = """
                  {
                  "name": "Pokemon Center Senica",
                  "city": "Senica",
                  "street": "Dolnohorniarska 199",
                  "country": "Slovakia",
                  "phoneNumber": "0948775092",
                  "email": "pokemon.center.senica@info.sk",
                  "specialization": "All types"
                }""";

        given()
            .contentType("application/json")
            .body(requestBody)
        .when()
            .post("/api/pokemon-centers")
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void shouldGetAllPokemonCenters(){
        List<PokemonCenterResponse> response =
            given()
                .contentType("application/json")
            .when()
                .get("/api/pokemon-centers")
            .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", PokemonCenterResponse.class);

        assertEquals(1, response.size());
    }

//    @Test
//    void shouldGetPokemonCenterById(){
//        Long pokemonCenterId = 0L;
//
//        given()
//            .contentType("application/json")
//        .when()
//            .get("/api/pokemon-centers/" + pokemonCenterId)
//        .then()
//            .statusCode(HttpStatus.OK.value())
//            .body("name", equalTo("Pokemon Center Senica"))
//            .body("email", equalTo("pokemon.center.senica@info.sk"));
//    }
}
