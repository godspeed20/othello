package com.othello.exceptions;

import com.othello.Player;

public class PlayerCannotSkipMoveException extends SadException {
    public PlayerCannotSkipMoveException(Player player) {
        super("Player " + player + " has moves left, cannot skip their turn");
    }
}
