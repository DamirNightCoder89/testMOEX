package com.damirkan.shareservice;

import com.damirkan.shareservice.model.Share;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class ShareserviceApplication {

	public static void main(String[] args) {




		SpringApplication.run(ShareserviceApplication.class, args);


	}

	String uri = "https://iss.moex.com/iss/engines/stock/markets/shares/securities.json?iss.meta=off&iss.version=off&iss.json=extended&iss.only=marketdata&marketdata.columns=SECID,LAST";
	WebClient webClient = WebClient.create();

	@GetMapping("/tr")
	public  Mono<Object[]> retrive() {
		Mono<Object[]> shareMono = webClient.get()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Object[].class).log();
		System.out.println("yeu");
		return shareMono;

	}

	@GetMapping("/something")
	public String handle() {
		return "dfdf";
	}

}
