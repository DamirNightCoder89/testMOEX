package com.damirkan.shareservice;

import com.damirkan.shareservice.model.Share;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

	String uri = "https://iss.moex.com/iss/engines/stock/markets/shares/boards/TQBR/securities.json?iss.meta=off&iss.only=marketdata&marketdata.columns=SECID,LAST";
	WebClient webClient = WebClient.create();

	@GetMapping("/tr")
	public  void retrive() {
		Mono<Object[]> shareMono = webClient.get()
				.uri(uri)
				.retrieve()
				.bodyToMono(Object[].class).log();
		System.out.println("yeu");

	}

	@GetMapping("/something")
	public String handle() {
		return "dfdf";
	}

}
