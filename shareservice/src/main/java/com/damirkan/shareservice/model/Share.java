package com.damirkan.shareservice.model;

import org.springframework.lang.NonNull;


public class Share{
    public String id;
    public String last;

    public Share() {
    }

    public Share(String id, String last) {
        this.id = id;
        this.last = last;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
