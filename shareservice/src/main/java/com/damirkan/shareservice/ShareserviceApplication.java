package com.damirkan.shareservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.config.HypermediaWebClientConfigurer;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@SpringBootApplication
@RestController
public class ShareserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareserviceApplication.class, args);
	}

	@Bean
	WebClientCustomizer hypermediaWebClientCustomizer(HypermediaWebClientConfigurer configurer) {
		return webClientBuilder -> {
			configurer.registerHypermediaTypes(webClientBuilder);
		};
	}



}


