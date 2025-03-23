package com.sbelusky.microservices.user_service.config;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class that enables Micrometer observations on annotated methods.
 */
@Configuration
public class ObservationConfig {
    /**
     * Creates an instance of {@link ObservedAspect} that enables method-level observations.
     * @param registry the registry that stores the observations
     * @return an instance of {@link ObservedAspect}
     */
    @Bean
    ObservedAspect observedAspect(ObservationRegistry registry) {
        return new ObservedAspect(registry);
    }
}