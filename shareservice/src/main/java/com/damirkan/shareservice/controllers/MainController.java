package com.damirkan.shareservice.controllers;

import com.damirkan.shareservice.ShareserviceApplication;
import com.damirkan.shareservice.model.Share;
import com.damirkan.shareservice.model.Shares;
import com.damirkan.shareservice.service.ShareService;
import com.damirkan.shareservice.service.ShareServiceImplement;
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
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;


@RestController
public class MainController {

    private  final ShareService shareService;

    public MainController(ShareService shareService) {
        this.shareService = shareService;
    }

    @GetMapping("/shares/{id}")
    public Optional<Shares> retrive(@PathVariable String id) {
        return shareService.getShares(id);
    }

    @GetMapping("/shares")
    public Optional<Shares> retrivee() {
        return shareService.findAll();
    }
}







