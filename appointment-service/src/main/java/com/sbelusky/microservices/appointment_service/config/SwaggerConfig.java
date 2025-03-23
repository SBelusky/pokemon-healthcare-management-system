package com.sbelusky.microservices.appointment_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Swagger documentation.
 */
@Configuration
public class SwaggerConfig {
    /**
     * Creates OpenAPI configuration bean.
     *
     * @return OpenAPI configuration bean.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Appointment Service").version("1.0.0"));
    }
}
