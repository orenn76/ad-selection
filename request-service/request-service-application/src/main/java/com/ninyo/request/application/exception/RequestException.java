package com.ninyo.request.application.exception;

public class RequestException extends RuntimeException {

    private String message;

    public RequestException(String message) {
        super(message);
        this.message = message;
    }
}
