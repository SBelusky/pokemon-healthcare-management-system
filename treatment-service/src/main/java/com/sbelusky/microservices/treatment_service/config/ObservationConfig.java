package com.sbelusky.microservices.treatment_service.config;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Micrometer observations.
 */
@Configuration
public class ObservationConfig {
    /**
     * Creates and returns an instance of the ObservedAspect bean.
     * This bean is required for the auto-configuration of Micrometer observations.
     *
     * @param registry the ObservationRegistry instance
     * @return the ObservedAspect bean
     */
    @Bean
    ObservedAspect observedAspect(ObservationRegistry registry) {
        return new ObservedAspect(registry);
    }
}
