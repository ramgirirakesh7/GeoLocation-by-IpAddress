package com.user.registation.geo.module.geo.exception;

public class InvalidIPProvideException extends RuntimeException {
    private String message;
    public InvalidIPProvideException(String message) {
        super(message);
        this.message = message;
    }
    public InvalidIPProvideException() {}
}
