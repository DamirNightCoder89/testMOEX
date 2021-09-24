package com.damirkan.shareservice.model;

import com.damirkan.shareservice.util.CustomDesirializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(using = CustomDesirializer.class)
public class Shares {
    private List<Share> shares;

    public Shares() {
    }

    public Shares(List<Share> shares) {
        this.shares = shares;
    }

    public List<Share> getShares() {
        return shares;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
    }
}
