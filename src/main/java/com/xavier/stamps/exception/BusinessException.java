package com.xavier.stamps.exception;

public class BusinessException extends Exception {
    public BusinessException(String message) {
        super("Business Error - " + message);
    }
}
