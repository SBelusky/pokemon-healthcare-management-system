package com.sbelusky.microservices.treatment_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for setting up CORS (Cross-Origin Resource Sharing) settings.
 */
@Configuration
public class CorsConfig {

    /**
     * Bean definition for WebMvcConfigurer to configure CORS mappings.
     *
     * @return WebMvcConfigurer instance with customized CORS settings.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * Configure CORS mappings to allow cross-origin requests from specified origins.
             *
             * @param registry CorsRegistry to add CORS mappings.
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow requests to all endpoints.
                        .allowedOrigins("http://localhost:9000") // Allow specified origin.
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods.
                        .allowedHeaders("Authorization", "Content-Type") // Allowed request headers.
                        .allowCredentials(true); // Allow credentials in the request.
            }
        };
    }
}