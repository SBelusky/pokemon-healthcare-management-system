package com.sbelusky.microservices.pokemon_center_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for Swagger documentation.
 */
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     * Creates OpenAPI configuration bean.
     *
     * @return OpenAPI configuration bean.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Pokemon Center Service").version("1.0.0"));
    }
}

