package com.othello.exceptions;

public class TooBigAShiftException extends InvalidCommandException {
    public TooBigAShiftException() {
        super("Shift was too big to calculate, should be adjacent only");
    }
}
