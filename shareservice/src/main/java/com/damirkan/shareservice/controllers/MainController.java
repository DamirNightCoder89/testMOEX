package com.damirkan.shareservice.controllers;

import com.damirkan.shareservice.ShareserviceApplication;
import com.damirkan.shareservice.model.Share;
import com.damirkan.shareservice.model.Shares;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
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

import java.util.*;
import java.util.stream.Collectors;


@RestController
public class MainController {

    private static final String BASE_URL = "https://iss.moex.com/iss";
    private static final String SHARES_LIST_URL = "/engines/stock/markets/shares/securities.json?iss.meta=off&iss.version=off&iss.json=extended&iss.only=marketdata&marketdata.columns=SECID,LAST";

    WebClient webClient = WebClient.builder().baseUrl(BASE_URL).build();

    @GetMapping("/shares/{id}")
    public Shares retrive(@PathVariable String id) {
        StringBuffer stringBuffer = new StringBuffer("/engines/stock/markets/shares/securities.json?securities=");
        String share_uri = stringBuffer.append(id).append("&iss.meta=off&iss.version=off&iss.json=extended&iss.only=marketdata&marketdata.columns=SECID,LAST").toString();
        Mono<Shares> response = webClient.get()
                .uri(share_uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Shares.class);
        Shares shares = response.block();

        return shares;
    }

    @GetMapping("/shares")
    public CollectionModel<EntityModel<Share>> retrivee() throws JsonProcessingException {

        Mono<Shares> response = webClient.get()
                .uri(SHARES_LIST_URL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Shares.class);

        Shares shares = response.block();

        List<EntityModel<Share>> shares_list = shares.getShares().stream().map(share -> EntityModel.of(share)).collect(Collectors.toList());

        return CollectionModel.of(shares_list);
    }
}







