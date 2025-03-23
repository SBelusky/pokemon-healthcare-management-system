package com.sbelusky.microservices.appointment_service.stub;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class PokemonClientStub {

    public static void stubPokemonCall(String pokemonId) throws JsonProcessingException {
        String responseJson = """
            {
              "id": 2,
              "name": "Charry",
              "birthDate": "2023-02-01",
              "speciesResponse": {
                "id": 2,
                "name": "Charmander",
                "type": "Fire",
                "weight": "8.5",
                "photoImage": "charmander.png",
                "baseHappiness": "50",
                "captureRate": "45",
                "color": "Red",
                "habitat": "Mountain",
                "isMythical": false,
                "shape": "Bipedal",
                "growthRate": "Medium Slow"
              },
              "abilities": [
                {
                  "name": "Blaze"
                }
              ],
              "createdAt": "2025-03-03T14:04:56",
              "updatedAt": "2025-03-03T14:04:56"
            }
        """;

        stubFor(get(urlEqualTo("/api/pokemons/" + pokemonId))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseJson)));
    }
}
