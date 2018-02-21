package com.othello.exceptions;

import com.othello.Coordinate;

public class NonPermittedMoveException extends InvalidCommandException {
    private NonPermittedMoveException(final Coordinate coordinate, String reason) {
        super("Position " + coordinate.coordinate() + " " + reason + " and so is not a valid move, please enter a coordinate permissible to you");
    }

    public static NonPermittedMoveException notPermissableForPlayer(Coordinate coordinate) {
        return new NonPermittedMoveException(coordinate, "is not permitted for you to play");
    }

    public static NonPermittedMoveException notAdjacent(Coordinate coordinate) {
        return new NonPermittedMoveException(coordinate, "is not adjacent to an existing piece");
    }

    public static NonPermittedMoveException alreadyTaken(Coordinate coordinate) {
        return new NonPermittedMoveException(coordinate, "is already taken");
    }

}
