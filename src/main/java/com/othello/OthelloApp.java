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

    public static void main(String[] args) {
        Reader input = new InputStreamReader(System.in);
        OutputWriter output = new OutputWriter();

        OthelloApp app = new OthelloApp(input, output);
        app.run();
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
        output.writeLine("h - Player hint");
        output.writeLine("q - Quit Game");
        output.writeLine("");
    }

    public void run() {
        output.writeLine("Welcome to Othello");
        output.writeLine("");

        Scanner scanner = new Scanner(input);
        String line = "";

        while (!line.equals("q")) {
            game.renderBoard(output);
            output.write("Player " + game.currentPlayer() + " move: ");

            line = scanner.nextLine().toLowerCase();

            switch (line) {
                case "n":
                    game = new OthelloGame(Board.newBoard());
                    output.writeLine("");
                    output.writeLine("Starting New Game");
                    break;
                case "g":
                    userGuide(output);
                    break;
                case "q":
                    break;
                case "h":
                    output.writeLine("");
                    game.suggestNextMove(output);
                    break;
                default:
                    try {
                        game.applyCommand(line, output);
                    } catch (InvalidCommandException e) {
                        output.writeLine("Invalid move '" + line + "', please try again. Reason: " + e.getMessage());
                    }
                    break;
            }
        }
        output.writeLine("");
        output.writeLine("Thank you for playing. Goodbye!");
    }
}
