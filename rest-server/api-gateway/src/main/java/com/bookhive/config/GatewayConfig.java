package com.bookhive.config;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
//@EnableHystrix
public class GatewayConfig {

    private AuthenticationFilter filter;


    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://user-service"))
                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))
                .build();
    }


}
