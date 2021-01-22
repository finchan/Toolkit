package com.xavier.stamps.exception;

public class DBException extends Exception {
    public DBException(String message) {
        super("DB Operation Error - " + message);
    }
}
