package com.ironhack.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()

                .route(p -> p.path("/api/exercises/**")
                        .uri("lb://TRAINING-SERVICE"))
                .route(p -> p.path("/api/exercise-sessions/**")
                        .uri("lb://TRAINING-SERVICE"))
                .route(p -> p.path("/api/exercise-types/**")
                        .uri("lb://TRAINING-SERVICE"))
                .route(p -> p.path("/api/programs/**")
                        .uri("lb://TRAINING-SERVICE"))
                .route(p -> p.path("/api/repetitions/**")
                        .uri("lb://TRAINING-SERVICE"))
                .route(p -> p.path("/api/sets/**")
                        .uri("lb://TRAINING-SERVICE"))
                .route(p -> p.path("/api/workouts/**")
                        .uri("lb://TRAINING-SERVICE"))

                .route(p -> p.path("/api/users/**")
                        .uri("lb://USER-SERVICE"))

                .build();
    }
}
