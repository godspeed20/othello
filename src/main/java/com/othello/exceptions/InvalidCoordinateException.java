package com.othello.exceptions;

public class InvalidCoordinateException extends InvalidCommandException {
    public InvalidCoordinateException() {
        super("Coordinate could not be calculated, most likely impossible, should be a valid board reference");
    }

    public InvalidCoordinateException(String coordinate) {
        super("Coordinate " + coordinate + " is invalid, should be a valid board reference");
    }
}
