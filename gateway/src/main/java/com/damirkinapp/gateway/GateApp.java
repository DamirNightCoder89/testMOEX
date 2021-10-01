package com.damirkinapp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@SpringBootApplication
@RestController
@EnableConfigurationProperties(UriConfiguration.class)
public class GateApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(GateApp.class, args);
    }

//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
//        String httpUri = uriConfiguration.getHttpbin();
//        return builder.routes()
//                .route(p -> p
//                        .path("/shares")
//                        .uri(httpUri))
//                .routes()
//                .route(p -> p
//                        .path("/shares/ticker")
//                        .uri(httpUri))
//                .build();
//    }
//    // end::route-locator[]

    // tag::fallback[]
    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }

}

@ConfigurationProperties
class UriConfiguration {

    private String httpbin = "http://localhost:6001";

    public String getHttpbin() {
        return httpbin;
    }

    public void setHttpbin(String httpbin) {
        this.httpbin = httpbin;
    }
}
