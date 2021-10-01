package com.damirkinapp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(UriConfiguration.class)
public class GateApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(GateApp.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
        String httpUri = uriConfiguration.getHttpbin();
        return builder.routes()
                .route(p -> p
                        .path("/shares")
                        .uri(httpUri))
                .build();
    }
    // end::route-locator[]

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

class Share {

    private String boardId;
    private String ticker;
    private String lastPrice;

    public Share() {
    }

    public Share(String boardId, String ticker, String lastPrice) {
        this.boardId = boardId;
        this.ticker = ticker;
        this.lastPrice = lastPrice;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }
}

class Shares {
    private List<Share> data;

    public Shares() {
    }

    public Shares(List<Share> data) {
        this.data = data;
    }

    public List<Share> getData() {
        return data;
    }

    public void setData(List<Share> data) {
        this.data = data;
    }
}
