package com.othello.exceptions;

public abstract class SadException extends RuntimeException {
    public SadException(String message) {
        super(message);
    }
}
