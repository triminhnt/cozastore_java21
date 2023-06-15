package com.cybersoft.cozastore_java21.exception;

public class CustomeFileNotFoundException extends  RuntimeException{

    private int status;
    private String message;

    public CustomeFileNotFoundException() {}
    public CustomeFileNotFoundException(int status, String message) {

        this.status = status;
        this.message = message;
    }
}
