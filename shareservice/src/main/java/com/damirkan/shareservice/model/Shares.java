package com.damirkan.shareservice.model;

import java.util.List;

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
