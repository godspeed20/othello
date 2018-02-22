package com.othello;

public enum Player {
    X, O;

    public static Player opponentOf(Player player) {
        return player.equals(X) ? O : X;
    }
}
