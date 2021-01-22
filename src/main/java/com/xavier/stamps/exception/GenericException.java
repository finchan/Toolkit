package com.xavier.stamps.exception;

public class GenericException extends RuntimeException {
    public GenericException(String message) {
        super("Generic Exception - Runtime");
    }
}
