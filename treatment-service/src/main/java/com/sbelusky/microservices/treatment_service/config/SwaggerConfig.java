package com.sbelusky.microservices.treatment_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

/**
 * Configuration class for setting up the OpenAPI documentation for the Treatment Service.
 */
public class SwaggerConfig {

    /**
     * Creates a custom OpenAPI bean with specified information about the API.
     *
     * @return an OpenAPI instance containing metadata about the Treatment Service API.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Treatment Service").version("1.0.0"));
    }
}
