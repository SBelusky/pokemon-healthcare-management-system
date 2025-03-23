package com.sbelusky.microservices.pokemon_service.config;

import com.sbelusky.microservices.pokemon_service.client.UserClient;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

/**
 * Configuration class for creating REST clients.
 */
@Configuration
public class RestClientConfig {

    @Value("${user.url}")
    private String userServiceUrl;

    private final ObservationRegistry registry;

    public RestClientConfig(ObservationRegistry registry) {
        this.registry = registry;
    }

    /**
     * Creates a REST client to call the user service.
     *
     * @return UserClient instance
     */
    @Bean
    public UserClient userClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(userServiceUrl)
                .requestFactory(getUserRequestFactory())
                .observationRegistry(registry)
                .build();

        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return httpServiceProxyFactory.createClient(UserClient.class);
    }

    /**
     * Creates a client request factory for the user service.
     *
     * @return ClientHttpRequestFactory instance
     */
    private ClientHttpRequestFactory getUserRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout((int) Duration.ofSeconds(3).toMillis());
        factory.setReadTimeout((int) Duration.ofSeconds(3).toMillis());

        return factory;
    }
}
