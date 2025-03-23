package com.sbelusky.microservices.pokemon_service.config;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Micrometer observations.
 * It creates the ObservedAspect bean needed for the annotations to work.
 */
@Configuration
public class ObservationConfig {
    /**
     * Creates the ObservedAspect bean needed for the annotations to work.
     * @param registry the ObservationRegistry to use
     * @return the ObservedAspect bean
     */
    @Bean
    ObservedAspect observedAspect(ObservationRegistry registry) {
        return new ObservedAspect(registry);
    }
}