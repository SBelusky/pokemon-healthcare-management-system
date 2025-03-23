package com.sbelusky.microservices.notification_service.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

/**
 * Configuration class for setting up Kafka Observability.
 * It is used to configure the {@link ConcurrentKafkaListenerContainerFactory}
 * for enabling observation of Kafka listener containers.
 */
@Configuration
public class ObservabilityConfig {
    private final ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory;

    public ObservabilityConfig(ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory) {
        this.concurrentKafkaListenerContainerFactory = concurrentKafkaListenerContainerFactory;
    }

    /**
     * Post construct method that enables observation of Kafka listener containers.
     * It sets the observation enabled property to true.
     */
    @PostConstruct
    public void setConcurrentKafkaListenerContainerFactory(){
        concurrentKafkaListenerContainerFactory.getContainerProperties().setObservationEnabled(true);
    }
}
