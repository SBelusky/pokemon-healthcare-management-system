package com.sbelusky.microservices.appointment_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for CORS mapping. It allows requests from http://localhost:9000 with GET, POST, PUT, DELETE and OPTIONS methods.
 * It also allows headers "Authorization" and "Content-Type" and allows credentials.
 */
@Configuration
public class CorsConfig {

    /**
     * Creates a WebMvcConfigurer that adds the CORS mapping to the registry.
     * @return the WebMvcConfigurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:9000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("Authorization", "Content-Type")
                        .allowCredentials(true);
            }
        };
    }
}