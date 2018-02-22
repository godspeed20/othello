package com.othello.exceptions;

public class TooBigAShiftException extends SadException {
    public TooBigAShiftException() {
        super("Shift was too big to calculate, should be adjacent only");
    }
}
