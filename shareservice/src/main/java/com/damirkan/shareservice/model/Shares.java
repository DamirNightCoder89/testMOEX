package com.damirkan.shareservice.model;

import com.damirkan.shareservice.util.CustomSharesDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@JsonDeserialize(using = CustomSharesDeserializer.class)
public class Shares extends RepresentationModel<Shares> {
    private List<Share> data;

    public Shares() {
    }

    public Shares(List<Share> data) {
        this.data = data;
    }

    public List<Share> getData() {
        return data;
    }

    public void setData(List<Share> data) {
        this.data = data;
    }
}
