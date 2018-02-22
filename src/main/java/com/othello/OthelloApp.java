package com.othello;

import com.othello.exceptions.InvalidCommandException;
import com.othello.writer.OutputWriter;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

public class OthelloApp {
    private OthelloGame game;
    private Reader input;
    private OutputWriter output;

    public OthelloApp(Reader input, OutputWriter output) {
        this.input = input;
        this.output = output;
        game = new OthelloGame(Board.newBoard());
    }

    private void userGuide(OutputWriter output) {
        output.writeLine("");
        output.writeLine("User Guide: ");
        output.writeLine("You must 'capture' your opponents piece, by placing your piece in a square adjacent to an existing piece, trapping one or more of your opponents' pieces between your own.");
        output.writeLine("The game ends when no more moves are possible.");
        output.writeLine("The player with the most pieces on the board at the end of the game wins.");
        output.writeLine("");
        output.writeLine("Permitted commands:");
        output.writeLine("n - New Game");
        output.writeLine("g - User Guide");
        output.writeLine("coordinate - place your next piece");
        output.writeLine("q - Quit Game");
        output.writeLine("");
    }

    public static void main(String[] args) {
        Reader input = new InputStreamReader(System.in);
        OutputWriter output = new OutputWriter();

        OthelloApp app = new OthelloApp(input, output);
        app.run();
    }

    public void run() {
        output.writeLine("Welcome to Othello");

        Scanner scanner = new Scanner(input);
        String line = "n";

        while (!line.equals("q")) {
            switch (line) {
                case "n":
                    game = new OthelloGame(Board.newBoard());
                    output.writeLine("");
                    output.writeLine("Starting New Game");
                    game.renderBoard(output);
                    break;
                case "g":
                    userGuide(output);
                    break;
                case "q":
                    break;
                default:
                    try {
                        game.applyCommand(line);
                        game.renderBoard(output);
                    } catch (InvalidCommandException e) {
                        output.writeLine("Invalid move '" + line + "', please try again. Reason: " + e.getMessage());
                    }
                    break;
            }

            verifyCurrentPlayerCanPlay(output);

            if (!game.currentPlayerHasMovesAvailable()) {
                gameOverSummary(output);
                line = "q";
            }else {
                output.write("Player " + game.currentPlayer() + " move: ");
                line = scanner.nextLine().toLowerCase();
            }

        }
        output.writeLine("");
        output.writeLine("Thank you for playing. Goodbye!");
    }

    private void verifyCurrentPlayerCanPlay(OutputWriter output) {
        if (game.currentPlayerHasMovesAvailable()) return;

        output.writeLine("No moves available for player '" + game.currentPlayer() + "', skipping");
        game.skipMove();
    }

    private void gameOverSummary(OutputWriter output) {
        output.writeLine("No further moves possible. Game over!");

        Player winner = game.pointsFor(Player.X) > game.pointsFor(Player.O) ? Player.X : Player.O;
        output.writeLine("Player " + winner + " wins! ( " + game.pointsFor(winner) + " vs " + game.pointsFor(Player.opponentOf(winner)) + " )");
    }
}
