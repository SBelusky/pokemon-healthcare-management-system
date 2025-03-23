package com.sbelusky.microservices.pokemon_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for Swagger.
 */
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     * Returns OpenAPI configuration.
     *
     * @return OpenAPI configuration.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        // Creates OpenAPI configuration with title and version.
        return new OpenAPI().info(new Info().title("Pokemon Service").version("1.0.0"));
    }
}