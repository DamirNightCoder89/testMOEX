package com.damirkinapp.histroyservice.service;

import com.damirkinapp.histroyservice.model.HistShare;
import com.damirkinapp.histroyservice.model.HistShares;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class HistShareServiceImplement implements HistShareService {

    private static final String BASE_URL = "https://iss.moex.com/iss";

    WebClient webClient = WebClient.builder().baseUrl(BASE_URL).build();

    @Override
    @Cacheable("addresses")
    public Optional<HistShares> getHistShares(String id, String dateFrom, String dateTo) {
        String share_uri = "/history/engines/stock/markets/shares/boards/tqbr/securities/"
                + id + ".json?iss.meta=off&iss.json=extended&from="
                + dateFrom + "&till=" + dateTo;
        System.out.println(share_uri);
        Optional<HistShares> response = webClient.get()
                .uri(share_uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(HistShares.class)
                .onErrorResume(WebClientResponseException.class, ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex))
                .blockOptional();
        
        return response;
    }

}
