package com.damirkan.shareservice;

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
import lombok.var;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.HypermediaWebClientConfigurer;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@SpringBootApplication
@RestController
public class ShareserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareserviceApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		var mapper = new ObjectMapper();

		SimpleModule module = new SimpleModule("CustomDesirializer", new Version(1, 0, 0, null, null, null));
		module.addDeserializer(Shares.class, new CustomDesirializer());
		mapper.registerModule(module);
		mapper.findAndRegisterModules();
		return mapper;
	}

	@Bean
	Jackson2JsonEncoder jackson2JsonEncoder(ObjectMapper mapper) {
		return new Jackson2JsonEncoder(mapper);
	}
	@Bean
	Jackson2JsonDecoder jackson2JsonDecoder(ObjectMapper mapper) {
		return new Jackson2JsonDecoder(mapper);
	}

	@Bean
	WebFluxConfigurer webFluxConfigurer(Jackson2JsonEncoder encoder, Jackson2JsonDecoder decoder) {
		return new WebFluxConfigurer() {
			@Override
			public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
				configurer.defaultCodecs().jackson2JsonEncoder(encoder);
				configurer.defaultCodecs().jackson2JsonDecoder(decoder);
			}
		};
	}



}

class CustomDesirializer extends StdDeserializer<Shares> {
	public CustomDesirializer() {
		this(null);
	}

	public CustomDesirializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Shares deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		Shares shares = new Shares();
		List<Share> map = new ArrayList<>();
		JsonNode nextElement;
		ObjectCodec codec = jsonParser.getCodec();
		JsonNode node = codec.readTree(jsonParser);

		Iterator<JsonNode> rootElements = node.elements();
		while (rootElements.hasNext()) {
			nextElement = rootElements.next();
			if(nextElement.has("marketdata")) {
				map = StreamSupport.stream(nextElement.path("marketdata").spliterator(), false).map(a -> {
					return new Share(a.get("SECID").asText(), a.get("LAST").asText());
				}).collect(Collectors.toList());
			}
		}
		shares.setShares(map);
		return shares;
	}



}


