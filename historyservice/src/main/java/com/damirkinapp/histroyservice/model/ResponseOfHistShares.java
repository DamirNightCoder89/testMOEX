package com.damirkinapp.histroyservice.model;

import java.time.Instant;

public class ResponseOfHistShares {

    private HistShares resp;
    private Long responseTime;

    public ResponseOfHistShares(HistShares responce) {
        this.resp = responce;
        this.responseTime = Instant.now().getEpochSecond();
    }

    public HistShares getResp() {
        return resp;
    }

    public void setResp(HistShares resp) {
        this.resp = resp;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }
}
