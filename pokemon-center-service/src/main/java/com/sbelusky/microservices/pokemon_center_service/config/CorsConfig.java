package com.sbelusky.microservices.pokemon_center_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for CORS (Cross-Origin Resource Sharing).
 *
 * By default, web browsers enforce same-origin policy, which means that a web page can only request resources from the same origin (domain, protocol, or port). CORS is a mechanism that allows a web page to request resources from a different origin by adding a special header to the request.
 *
 * This configuration allows requests from the specified origin to be processed by the application.
 */
@Configuration
public class CorsConfig {

    /**
     * Creates a {@link WebMvcConfigurer} bean that configures CORS mappings.
     *
     * @return a {@link WebMvcConfigurer} instance
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            /**
             * Configures CORS mappings for the application.
             *
             * @param registry a {@link CorsRegistry} instance
             */
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