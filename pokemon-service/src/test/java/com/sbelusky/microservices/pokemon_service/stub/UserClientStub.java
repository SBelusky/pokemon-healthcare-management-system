package com.sbelusky.microservices.pokemon_service.stub;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class UserClientStub {

    public static void stubUserCall(String userId) throws JsonProcessingException {
        String responseJson = """
            {
                  "userName": "sbelusky",
                  "email": "samuel.belusky@gmail.com",
                  "firstName": "Samuel",
                  "lastName": "Beluský",
                  "role": "trainer",
                  "createdAt": "2025-02-13T15:04:06",
                  "updatedAt": "2025-03-03T13:37:29"
            }
        """;

        stubFor(get(urlEqualTo("/api/users/" + userId))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseJson)));
    }
}
