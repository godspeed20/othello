package com.othello;

public enum Player {
    A("X"), B("O");

    public final String playerName;

    Player(String playerName) {
        this.playerName = playerName;
    }

    public static Player opponentOf(Player player) {
        return player.equals(A) ? B : A;
    }
}
