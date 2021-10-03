package com.damirkinapp.histroyservice.exeptions;

public class ShareNotFoundException extends RuntimeException {

    public ShareNotFoundException() {
        super("Could not find share ");
    }

    public ShareNotFoundException(String id) {
        super("Could not find share " + id);
    }

}
