package com.user.registation.geo.module.geo.exception;

public class InvalidUserDetailsException extends RuntimeException {
    private String message;
    public  InvalidUserDetailsException(String message) {
        super(message);
        this.message = message;
    }
    public InvalidUserDetailsException() {}
}
