package com.damirkan.shareservice.model;

import java.time.Instant;

public class ResponseOfShares {

    private Shares resp;
    private Long responseTime;

    public ResponseOfShares(Shares responce) {
        this.resp = responce;
        this.responseTime = Instant.now().getEpochSecond();
    }

    public Shares getResp() {
        return resp;
    }

    public void setResp(Shares resp) {
        this.resp = resp;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }
}
