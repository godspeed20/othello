package com.othello;

import com.othello.writer.OutputWriter;

import java.util.Optional;

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

    public void applyCommand(String line, OutputWriter output) {
        board = board.validateAndApply(line);

        verifyCanContinueGame(output);
    }

    void verifyCanContinueGame(OutputWriter output) {
        if (board.bestNextMove().isPresent()) return;

        output.writeLine("No moves available for player '" + currentPlayer() + "', skipping");
        board = board.skipMove();

        if (!board.bestNextMove().isPresent()) gameOverSummary(output);
    }

    public void suggestNextMove(OutputWriter output) {
        Optional<Coordinate> bestNextMove = board.bestNextMove();
        bestNextMove.ifPresent(coordinate -> output.writeLine("Player " + currentPlayer() + " best next move: " + coordinate.coordinate()));
    }

    private void gameOverSummary(OutputWriter output) {
        output.writeLine("No further moves possible. Game over!");

        Player winner = board.pointsFor(Player.X) > board.pointsFor(Player.O) ? Player.X : Player.O;
        output.writeLine("Player " + winner + " wins! ( " + board.pointsFor(winner) + " vs " + board.pointsFor(Player.opponentOf(winner)) + " )");
    }
}
