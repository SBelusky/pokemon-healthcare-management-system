package com.sbelusky.microservices.appointment_service.config;

import com.sbelusky.microservices.appointment_service.client.PokemonClient;
import com.sbelusky.microservices.appointment_service.client.UserClient;
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
 * Configuration class for creating REST clients for communication with
 * the user and pokemon services.
 */
@Configuration
public class RestClientConfig {
    /**
     * The URL of the user service
     */
    @Value("${user.url}")
    private String userServiceUrl;

    /**
     * The URL of the pokemon service
     */
    @Value("${pokemon.url}")
    private String pokemonServiceUrl;

    /**
     * The observation registry for the clients
     */
    private final ObservationRegistry registry;

    /**
     * Creates a new instance of the REST client config.
     * @param registry the observation registry
     */
    public RestClientConfig(ObservationRegistry registry) {
        this.registry = registry;
    }

    /**
     * Creates a REST client for the user service.
     * @return the user client
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
     * Creates a REST client for the pokemon service.
     * @return the pokemon client
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
     * Returns a request factory with a connection and read timeout of 3 seconds.
     * @return the request factory
     */
    private ClientHttpRequestFactory getRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout((int) Duration.ofSeconds(3).toMillis());
        factory.setReadTimeout((int) Duration.ofSeconds(3).toMillis());

        return factory;
    }
}