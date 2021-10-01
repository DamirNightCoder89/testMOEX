package com.damirkan.shareservice.model;

import java.time.Instant;

public class ResponseOfShare {

    private Share resp;
    private Long responseTime;

    public ResponseOfShare(Share responce) {
        this.resp = responce;
        this.responseTime = Instant.now().getEpochSecond();
    }

    public Share getResp() {
        return resp;
    }

    public void setResp(Share resp) {
        this.resp = resp;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }
}
