package com.in28minutes.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator getewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/currency-exchange/**")
                             .uri("lb://CURRENCY-EXCHANGE"))
                .route(p -> p.path("/currency-conversion/**")
                             .uri("lb://CURRENCY-CONVERSION"))
                .route(p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://CURRENCY-CONVERSION"))
                .route(p -> p.path("/currency-conversion-new/**")
                        .filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)",
                                                    "/currency-conversion-feign/${segment}"))
                        .uri("lb://CURRENCY-CONVERSION"))
                .build();
    }
}
