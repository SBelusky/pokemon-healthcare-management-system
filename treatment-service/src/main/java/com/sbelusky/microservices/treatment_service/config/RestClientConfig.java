package com.sbelusky.microservices.treatment_service.config;

import com.sbelusky.microservices.treatment_service.client.PokemonClient;
import com.sbelusky.microservices.treatment_service.client.UserClient;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

/**
 * Configuration class for creating REST clients for User and Pokemon services.
 */
@Configuration
public class RestClientConfig {
    /**
     * URL for User service.
     */
    @Value("${user.url}")
    private String userServiceUrl;

    /**
     * URL for Pokemon service.
     */
    @Value("${pokemon.url}")
    private String pokemonServiceUrl;

    /**
     * Observation registry for metrics.
     */
    private final ObservationRegistry registry;

    /**
     * Constructor for creating REST clients.
     * @param registry observation registry for metrics
     */
    public RestClientConfig(ObservationRegistry registry) {
        this.registry = registry;
    }

    /**
     * Creates a REST client for User service.
     * @return a UserClient instance
     */
    @Bean
    public UserClient userClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(userServiceUrl)
                .requestFactory(getRequestFactory())
                .observationRegistry(registry)
                .build();

        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return httpServiceProxyFactory.createClient(UserClient.class);
    }

    /**
     * Creates a REST client for Pokemon service.
     * @return a PokemonClient instance
     */
    @Bean
    public PokemonClient pokemonClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(pokemonServiceUrl)
                .requestFactory(getRequestFactory())
                .observationRegistry(registry)
                .build();

        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return httpServiceProxyFactory.createClient(PokemonClient.class);
    }

    /**
     * Creates a request factory with connection and read timeouts of 3 seconds.
     * @return a ClientHttpRequestFactory instance
     */
    private ClientHttpRequestFactory getRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout((int) Duration.ofSeconds(3).toMillis());
        factory.setReadTimeout((int) Duration.ofSeconds(3).toMillis());

        return factory;
    }
}