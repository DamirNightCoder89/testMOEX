package com.damirkan.shareservice.service;

import com.damirkan.shareservice.model.Share;
import com.damirkan.shareservice.model.Shares;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ShareServiceImplement implements ShareService {

    private static final String BASE_URL = "https://iss.moex.com/iss";
    private static final String SHARES_LIST_URL = "/engines/stock/markets/shares/securities.json?iss.meta=off&iss.version=off&iss.json=extended&iss.only=marketdata&marketdata.columns=BOARDID,SECID,LAST";

    WebClient webClient = WebClient.builder().baseUrl(BASE_URL).build();

    @Override
    public Optional<Shares> findAll() {

        Optional<Shares> response = webClient.get()
                .uri(SHARES_LIST_URL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Shares.class)
                .onErrorResume(WebClientResponseException.class, ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex))
                .blockOptional();

        return response;
    }

    @Override
    public Optional<Share> getShare(String id) {
        StringBuffer stringBuffer = new StringBuffer("/engines/stock/markets/shares/securities.json?securities=");
        String share_uri = stringBuffer.append(id).append("&iss.meta=off&iss.version=off&iss.json=extended&iss.only=marketdata&marketdata.columns=BOARDID,SECID,LAST").toString();
        Optional<Shares> response = webClient.get()
                .uri(share_uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Shares.class)
                .onErrorResume(WebClientResponseException.class, ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex))
                .blockOptional();
        
        return response.get().getData().stream().findFirst();
    }
}
