package com.sbelusky.microservices.pokemon_center_service.config;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up observation aspects.
 * This class registers an ObservedAspect bean, which facilitates
 * the observation of method executions within the application.
 */
@Configuration
public class ObservationConfig {

    /**
     * Creates and returns an ObservedAspect bean.
     *
     * @param registry the ObservationRegistry used to manage observations
     * @return an instance of ObservedAspect configured with the provided registry
     */
    @Bean
    public ObservedAspect observedAspect(ObservationRegistry registry) {
        return new ObservedAspect(registry);
    }
}