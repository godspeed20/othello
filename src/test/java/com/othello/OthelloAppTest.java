package com.othello;

import com.othello.writer.MockWriter;
import org.junit.Test;

import java.io.StringReader;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class OthelloAppTest {
    @Test
    public void canStartAndQuitGame() {
        StringReader input = new StringReader("Q");
        MockWriter output = new MockWriter();

        OthelloApp app = new OthelloApp(input, output);
        app.run();

        assertThat(output.getLines(), equalTo(newArrayList(
                "Welcome to Othello",
                "",
                "1 --------",
                "2 --------",
                "3 --------",
                "4 ---OX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player X move: ",
                "",
                "Thank you for playing. Goodbye!"
        )));
    }

    @Test
    public void canMakeMoveWithUpperCaseCoordinates() {
        StringReader input = new StringReader("C4\nQ");
        MockWriter output = new MockWriter();

        OthelloApp app = new OthelloApp(input, output);
        app.run();

        assertThat(output.getLines(), equalTo(newArrayList(
                "Welcome to Othello",
                "",
                "1 --------",
                "2 --------",
                "3 --------",
                "4 ---OX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player X move: ",
                "1 --------",
                "2 --------",
                "3 --------",
                "4 --XXX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player O move: ",
                "",
                "Thank you for playing. Goodbye!"
        )));
    }

    @Test
    public void sampleOutput() {
        StringReader input = new StringReader("3d\nc5\ne7\ne6\n5f\nQ");
        MockWriter output = new MockWriter();

        OthelloApp app = new OthelloApp(input, output);
        app.run();

        assertThat(output.getLines(), equalTo(newArrayList(
                "Welcome to Othello",
                "",
                "1 --------",
                "2 --------",
                "3 --------",
                "4 ---OX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player X move: ",
                "1 --------",
                "2 --------",
                "3 ---X----",
                "4 ---XX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player O move: ",
                "1 --------",
                "2 --------",
                "3 ---X----",
                "4 ---XX---",
                "5 --OOO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player X move: ",
                "Invalid move 'e7', please try again. Reason: Position e7 is not adjacent to an existing piece and so is not a valid move, please enter a coordinate permissible to you",
                "1 --------",
                "2 --------",
                "3 ---X----",
                "4 ---XX---",
                "5 --OOO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player X move: ",
                "1 --------",
                "2 --------",
                "3 ---X----",
                "4 ---XX---",
                "5 --OOX---",
                "6 ----X---",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player O move: ",
                "1 --------",
                "2 --------",
                "3 ---X----",
                "4 ---XX---",
                "5 --OOOO--",
                "6 ----X---",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player X move: ",
                "",
                "Thank you for playing. Goodbye!"
        )));
    }

    @Test
    public void canRequestAHint() {
        StringReader input = new StringReader("h\nq");
        MockWriter output = new MockWriter();

        OthelloApp app = new OthelloApp(input, output);
        app.run();

        assertThat(output.getLines(), equalTo(newArrayList(
                "Welcome to Othello",
                "",
                "1 --------",
                "2 --------",
                "3 --------",
                "4 ---OX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player X move: ",
                "",
                "Player X best next move: e6",
                "1 --------",
                "2 --------",
                "3 --------",
                "4 ---OX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player X move: ",
                "",
                "Thank you for playing. Goodbye!"
        )));
    }

    @Test
    public void canMakeMoveWithLowerCaseCoordinates() {
        StringReader input = new StringReader("c4\nq");
        MockWriter output = new MockWriter();

        OthelloApp app = new OthelloApp(input, output);
        app.run();

        assertThat(output.getLines(), equalTo(newArrayList(
                "Welcome to Othello",
                "",
                "1 --------",
                "2 --------",
                "3 --------",
                "4 ---OX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player X move: ",
                "1 --------",
                "2 --------",
                "3 --------",
                "4 --XXX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player O move: ",
                "",
                "Thank you for playing. Goodbye!"
        )));
    }

    @Test
    public void canStartNewGame() {
        StringReader input = new StringReader("c4\nn\nq");
        MockWriter output = new MockWriter();

        OthelloApp app = new OthelloApp(input, output);
        app.run();

        assertThat(output.getLines(), equalTo(newArrayList(
                "Welcome to Othello",
                "",
                "1 --------",
                "2 --------",
                "3 --------",
                "4 ---OX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player X move: ",
                "1 --------",
                "2 --------",
                "3 --------",
                "4 --XXX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player O move: ",
                "",
                "Starting New Game",
                "1 --------",
                "2 --------",
                "3 --------",
                "4 ---OX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player X move: ",
                "",
                "Thank you for playing. Goodbye!"
        )));
    }

    @Test
    public void canAskForGuide() {
        StringReader input = new StringReader("g\nq");
        MockWriter output = new MockWriter();

        OthelloApp app = new OthelloApp(input, output);
        app.run();

        assertThat(output.getLines(), equalTo(newArrayList(
                "Welcome to Othello",
                "",
                "1 --------",
                "2 --------",
                "3 --------",
                "4 ---OX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player X move: ",
                "",
                "User Guide: ",
                "You must 'capture' your opponents piece, by placing your piece in a square adjacent to an existing piece, trapping one or more of your opponents' pieces between your own.",
                "The game ends when no more moves are possible.",
                "The player with the most pieces on the board at the end of the game wins.",
                "",
                "Permitted commands:",
                "n - New Game",
                "g - User Guide",
                "coordinate - place your next piece",
                "h - Player hint",
                "q - Quit Game",
                "",
                "1 --------",
                "2 --------",
                "3 --------",
                "4 ---OX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "",
                "Player X move: ",
                "",
                "Thank you for playing. Goodbye!"
        )));
    }
}