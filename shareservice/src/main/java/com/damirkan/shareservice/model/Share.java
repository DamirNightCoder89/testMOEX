package com.damirkan.shareservice.model;

import org.springframework.lang.NonNull;


public class Share{
    public Long id;

    public Share() {
    }

    public Long  getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }
}
