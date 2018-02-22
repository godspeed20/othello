package com.othello;

import com.othello.writer.MockWriter;
import org.junit.Test;

import java.io.StringReader;
import java.util.List;

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
    public void canMakeMoveWithUpperCaseCoordinates() {
        StringReader input = new StringReader("C4\nQ");
        MockWriter output = new MockWriter();

        OthelloApp app = new OthelloApp(input, output);
        app.run();

        assertThat(output.getLines(), equalTo(newArrayList(
                "Welcome to Othello",
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
    public void canMakeMoveWithLowerCaseCoordinates() {
        StringReader input = new StringReader("c4\nq");
        MockWriter output = new MockWriter();

        OthelloApp app = new OthelloApp(input, output);
        app.run();

        assertThat(output.getLines(), equalTo(newArrayList(
                "Welcome to Othello",
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
                "User Guide: ",
                "You must 'capture' your opponents piece, by placing your piece in a square adjacent to an existing piece, trapping one or more of your opponents' pieces between your own.",
                "The game ends when no more moves are possible.",
                "The player with the most pieces on the board at the end of the game wins.",
                "",
                "Permitted commands:",
                "n - New Game",
                "g - User Guide",
                "coordinate - place your next piece",
                "q - Quit Game",
                "",
                "Player X move: ",
                "",
                "Thank you for playing. Goodbye!"
        )));
    }

    @Test
    public void correctlyReportsPlayerOWinsAndGameEnds() {
        StringReader input = new StringReader("g\ne6\nf6\ng6\ng7\ng8\nd6\nc6\nh8\nf5\nb6\na6\nd7\nc4\nh6\nd8\ne3\nf7\ne7\nh7\nf8\ne2\nb3\ne8\nf4\nd3\nc7\nb7\nd2\nd1\nc5\nc3\na7\nc8\nb8\na8\ng5\nf3\nf2\ng1\nc2\nb1\ne1\nb5\na5\ng4\nf1\ng2\ng3\nb4\na4\nh3\nc1\nb2\na2\na3\nh1\nh5\nh4\na1\nh2");
        MockWriter output = new MockWriter();

        OthelloApp app = new OthelloApp(input, output);
        app.run();

        List<String> lines = output.getLines();
        List<String> finalLines = lines.subList(lines.size() - 5, lines.size());
        assertThat(finalLines, equalTo(newArrayList(
                "No moves available for player 'X', skipping",
                "No further moves possible. Game over!",
                "Player O wins! ( 45 vs 19 )",
                "",
                "Thank you for playing. Goodbye!"
        )));
    }

    @Test
    public void correctlyReportsPlayerXRunningOutOfMovesButGameContinuing() {
        StringReader input = new StringReader("g\ne6\nf6\ng6\ng7\ng8\nh8\nf5\nf8\nf7\ne7\nd8\nd7\nc8\nc7\nd6\nh5\ne8\nc5\nb7\nc4\nh6\nh7\nc3\ng4\nb6\nc6\ng5\na8\nb8\na6\na5\na4\nb4\nf3\nf4\nb3\nh4\nb5\na3\nd2\nd3\ne2\ne3\nf2\ng2\nh2\nh1\nb2\nh3\na2\nf1\ng1\nd1\nc1\na1\nb1\nc2\na7\nq");
        MockWriter output = new MockWriter();

        OthelloApp app = new OthelloApp(input, output);
        app.run();

        List<String> lines = output.getLines();
        List<String> finalLines = lines.subList(lines.size() - 14, lines.size());
        assertThat(finalLines, equalTo(newArrayList(
                "1 XOOX-XOX",
                "2 OOXXXOXX",
                "3 OOXXOX-X",
                "4 OOOXXXXX",
                "5 OOOOXXXO",
                "6 OOOOOXXO",
                "7 OOOOOOOO",
                "8 OXXXXOOO",
                "  abcdefgh",
                "",
                "No moves available for player 'X', skipping",
                "Player O move: ",
                "",
                "Thank you for playing. Goodbye!"
        )));
    }
}