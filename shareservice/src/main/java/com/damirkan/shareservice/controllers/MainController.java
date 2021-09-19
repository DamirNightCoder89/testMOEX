package com.damirkan.shareservice.controllers;

import com.damirkan.shareservice.ShareserviceApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
public class MainController {

    String uri_list = "https://iss.moex.com/iss/engines/stock/markets/shares/securities.json?iss.meta=off&iss.version=off&iss.only=marketdata&marketdata.columns=SECID";
    String uri = "https://iss.moex.com/iss/engines/stock/markets/shares/securities.json?securities=GAZP&iss.meta=off&iss.version=off&iss.only=marketdata&marketdata.columns=,BOARDID,SECID,LAST";
    WebClient webClient = WebClient.create();

    @GetMapping("/shares/{id}")
    public Mono<String> retrive(@PathVariable Long id) {
        Mono<String> response = webClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class).log();


        return  response;
    }

    @GetMapping("/shares")
    public EntityModel<Root> retrivee() {

        Mono<Root> response = webClient.get()
                .uri(uri_list)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Root.class);



        Root root = response.block();
        EntityModel<Root> rot = EntityModel.of(root);
        return  rot;

    }

    @GetMapping("/something")
    public String handle() {
        return "dfdf";
    }
}

class Marketdata{
    //	public ArrayList<String> columns; 00305  03438
    public List<String> data;

    public Marketdata() {}

    public void setData(@NonNull ArrayList<ArrayList<String>> data) {
        List<String> dat = data.stream().map(a -> a.get(0)).collect(Collectors.toList());
        this.data = dat;

    }
}

class Root{
    public Marketdata marketdata;

    public Root(){}

    public Root(Marketdata marketdata) {
        this.marketdata = marketdata;
    }

    public Marketdata getMarketdata() {
        return marketdata;
    }

    public void setMarketdata(Marketdata marketdata) {
        this.marketdata = marketdata;
    }
}
