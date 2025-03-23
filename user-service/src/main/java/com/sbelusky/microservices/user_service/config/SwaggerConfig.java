package com.sbelusky.microservices.user_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for Swagger/OpenAPI.
 * It configures the Swagger documentation for the REST API.
 */
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     * Creates a bean of type OpenAPI which is used by Swagger to generate the API documentation.
     * @return an instance of OpenAPI with the configured API information
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("User Service").version("1.0.0"));
    }
}
