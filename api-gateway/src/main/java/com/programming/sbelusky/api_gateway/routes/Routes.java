package com.programming.sbelusky.api_gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.*;
import java.net.URI;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

/**
 * Routes configuration class.
 */
@Configuration
public class Routes {
    @Value("${pokemon.url}")
    private String pokemonServiceUrl;

    @Value("${user.url}")
    private String userServiceUrl;

    @Value("${pokemon-center.url}")
    private String pokemonCenterServiceUrl;

    @Value("${appointment.url}")
    private String appointmentServiceUrl;

    @Value("${treatment.url}")
    private String treatmentServiceUrl;

    /**
     * Pokemon service route.
     * @return router function
     */
    @Bean
    public RouterFunction<ServerResponse> pokemonServiceRoute(){
        return GatewayRouterFunctions.route("pokemon-service")
                .route(RequestPredicates.path("/api/pokemons/**"), HandlerFunctions.http(pokemonServiceUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("pokemonServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    /**
     * Pokemon service swagger route.
     * @return router function
     */
    @Bean
    public  RouterFunction<ServerResponse> pokemonServiceSwaggerRoute(){
        return GatewayRouterFunctions.route("pokemon-swagger")
                .route(RequestPredicates.path("/aggregate/pokemon-service/v3/api-docs"), HandlerFunctions.http(pokemonServiceUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("pokemonSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    /**
     * User service route.
     * @return router function
     */
    @Bean
    public RouterFunction<ServerResponse> userServiceRoute(){
        return GatewayRouterFunctions.route("user-service")
                .route(RequestPredicates.path("/api/users/**"), HandlerFunctions.http(userServiceUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("userServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    /**
     * User service swagger route.
     * @return router function
     */
    @Bean
    public  RouterFunction<ServerResponse> userServiceSwaggerRoute(){
        return GatewayRouterFunctions.route("user-swagger")
                .route(RequestPredicates.path("/aggregate/user-service/v3/api-docs"), HandlerFunctions.http(userServiceUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("userSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    /**
     * Pokemon center service route.
     * @return router function
     */
    @Bean
    public RouterFunction<ServerResponse> pokemonCenterServiceRoute(){
        return GatewayRouterFunctions.route("pokemon-center-service")
                .route(RequestPredicates.path("/api/pokemon-centers/**"), HandlerFunctions.http(pokemonCenterServiceUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("pokemonCenterServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    /**
     * Pokemon center service swagger route.
     * @return router function
     */
    @Bean
    public  RouterFunction<ServerResponse> pokemonCenterServiceSwaggerRoute(){
        return GatewayRouterFunctions.route("pokemon-center-swagger")
                .route(RequestPredicates.path("/aggregate/pokemon-center-service/v3/api-docs"), HandlerFunctions.http(pokemonCenterServiceUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("pokemonCenterSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    /**
     * Appointment service route.
     * @return router function
     */
    @Bean
    public RouterFunction<ServerResponse> appointmentServiceRoute(){
        return GatewayRouterFunctions.route("appointment-service")
                .route(RequestPredicates.path("/api/appointments/**"), HandlerFunctions.http(appointmentServiceUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("appointmentServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    /**
     * Appointment service swagger route.
     * @return router function
     */
    @Bean
    public  RouterFunction<ServerResponse> appointmentServiceSwaggerRoute(){
        return GatewayRouterFunctions.route("appointment-swagger")
                .route(RequestPredicates.path("/aggregate/appointment-service/v3/api-docs"), HandlerFunctions.http(appointmentServiceUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("appointmentSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    /**
     * Treatment service route.
     * @return router function
     */
    @Bean
    public RouterFunction<ServerResponse> treatmentServiceRoute(){
        return GatewayRouterFunctions.route("treatment-service")
                .route(RequestPredicates.path("/api/surgeries/**"), HandlerFunctions.http(treatmentServiceUrl))
                .route(RequestPredicates.path("/api/treatment-history/**"), HandlerFunctions.http(treatmentServiceUrl))
                .route(RequestPredicates.path("/api/prescriptions/**"), HandlerFunctions.http(treatmentServiceUrl))
                .route(RequestPredicates.path("/api/vaccination-plans/**"), HandlerFunctions.http(treatmentServiceUrl))
                .route(RequestPredicates.path("/api/medications/**"), HandlerFunctions.http(treatmentServiceUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("treatmentServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    /**
     * Treatment service swagger route.
     * @return router function
     */
    @Bean
    public  RouterFunction<ServerResponse> treatmentServiceSwaggerRoute(){
        return GatewayRouterFunctions.route("treatment-swagger")
                .route(RequestPredicates.path("/aggregate/treatment-service/v3/api-docs"), HandlerFunctions.http(treatmentServiceUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("treatmentSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    /**
     * Fall back route.
     * @return router function
     */
    @Bean
    public  RouterFunction<ServerResponse> fallBackRoute() {
        return GatewayRouterFunctions.route("fallbackRoute")
                .GET("/fallbackRoute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Service Unavailable"))
                .build();
    }
}
