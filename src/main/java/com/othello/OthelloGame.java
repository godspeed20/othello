package com.othello;

import com.othello.writer.OutputWriter;

public class OthelloGame {
    private Board board;

    public OthelloGame(Board board) {
        this.board = board;
    }

    public Player currentPlayer() {
        return board.currentPlayer;
    }

    public void renderBoard(OutputWriter output) {
        board.render(output);
    }

    public void applyCommand(String line) {
        board = board.validateAndApply(line);
    }

    public void skipMove() {
        board = board.skipMove();
    }

    public boolean currentPlayerHasMovesAvailable() {
        return board.playerCanMakeAMove();
    }

    public long pointsFor(Player player) {
        return board.pointsFor(player);
    }
}
