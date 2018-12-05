package com.webank.bdp.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class RouterApp {

    public static void main(String[] args) {
        SpringApplication.run(RouterApp.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        Function<PredicateSpec, Route.Builder> fn = new Function<PredicateSpec, Route.Builder>() {

            public Route.Builder apply(PredicateSpec t) {
                t.path("/api/upload");
                return t.uri("http://localhost:8001");
            }
        };
        return builder.routes().route(fn).build();
    }
}
