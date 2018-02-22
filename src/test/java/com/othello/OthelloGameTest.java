package com.othello;

import com.othello.writer.MockWriter;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class OthelloGameTest {
    @Test
    public void testPlayerOWins() {
        Board board = Board.newBoard()
                .validateAndApply("e6")
                .validateAndApply("f6")
                .validateAndApply("g6")
                .validateAndApply("g7")
                .validateAndApply("g8")
                .validateAndApply("d6")
                .validateAndApply("c6")
                .validateAndApply("h8")
                .validateAndApply("f5")
                .validateAndApply("b6")
                .validateAndApply("a6")
                .validateAndApply("d7")
                .validateAndApply("c4")
                .validateAndApply("h6")
                .validateAndApply("d8")
                .validateAndApply("e3")
                .validateAndApply("f7")
                .validateAndApply("e7")
                .validateAndApply("h7")
                .validateAndApply("f8")
                .validateAndApply("e2")
                .validateAndApply("b3")
                .validateAndApply("e8")
                .validateAndApply("f4")
                .validateAndApply("d3")
                .validateAndApply("c7")
                .validateAndApply("b7")
                .validateAndApply("d2")
                .validateAndApply("d1")
                .validateAndApply("c5")
                .validateAndApply("c3")
                .validateAndApply("a7")
                .validateAndApply("c8")
                .validateAndApply("b8")
                .validateAndApply("a8")
                .validateAndApply("g5")
                .validateAndApply("f3")
                .validateAndApply("f2")
                .validateAndApply("g1")
                .validateAndApply("c2")
                .validateAndApply("b1")
                .validateAndApply("e1")
                .validateAndApply("b5")
                .validateAndApply("a5")
                .validateAndApply("g4")
                .validateAndApply("f1")
                .validateAndApply("g2")
                .validateAndApply("g3")
                .validateAndApply("b4")
                .validateAndApply("a4")
                .validateAndApply("h3")
                .validateAndApply("c1")
                .validateAndApply("b2")
                .validateAndApply("a2")
                .validateAndApply("a3")
                .validateAndApply("h1")
                .validateAndApply("h5")
                .validateAndApply("h4")
                .validateAndApply("a1")
                .validateAndApply("h2");

        OthelloGame game = new OthelloGame(board);

        MockWriter output = new MockWriter();
        game.verifyCanContinueGame(output);
        assertThat(output.getLines(), equalTo(newArrayList(
                "No moves available for player 'X', skipping",
                "No further moves possible. Game over!",
                "Player O wins! ( 45 vs 19 )"
        )));

        output = new MockWriter();
        game.renderBoard(output);
        assertThat(output.getLines(), equalTo(newArrayList(
                "1 XXOOOOOO",
                "2 XXOOOOOO",
                "3 XXXXOOOO",
                "4 XXOOOOOO",
                "5 XXOOOXOO",
                "6 XXOOXOOO",
                "7 XXOOOOOO",
                "8 XOOOOOOO",
                "  abcdefgh",
                ""
        )));
    }

    @Test
    public void testPlayerXRunsOutOfMovesAndHasToBeSkipped() {
        Board board = Board.newBoard()
                .validateAndApply("e6")
                .validateAndApply("f6")
                .validateAndApply("g6")
                .validateAndApply("g7")
                .validateAndApply("g8")
                .validateAndApply("h8")
                .validateAndApply("f5")
                .validateAndApply("f8")
                .validateAndApply("f7")
                .validateAndApply("e7")
                .validateAndApply("d8")
                .validateAndApply("d7")
                .validateAndApply("c8")
                .validateAndApply("c7")
                .validateAndApply("d6")
                .validateAndApply("h5")
                .validateAndApply("e8")
                .validateAndApply("c5")
                .validateAndApply("b7")
                .validateAndApply("c4")
                .validateAndApply("h6")
                .validateAndApply("h7")
                .validateAndApply("c3")
                .validateAndApply("g4")
                .validateAndApply("b6")
                .validateAndApply("c6")
                .validateAndApply("g5")
                .validateAndApply("a8")
                .validateAndApply("b8")
                .validateAndApply("a6")
                .validateAndApply("a5")
                .validateAndApply("a4")
                .validateAndApply("b4")
                .validateAndApply("f3")
                .validateAndApply("f4")
                .validateAndApply("b3")
                .validateAndApply("h4")
                .validateAndApply("b5")
                .validateAndApply("a3")
                .validateAndApply("d2")
                .validateAndApply("d3")
                .validateAndApply("e2")
                .validateAndApply("e3")
                .validateAndApply("f2")
                .validateAndApply("g2")
                .validateAndApply("h2")
                .validateAndApply("h1")
                .validateAndApply("b2")
                .validateAndApply("h3")
                .validateAndApply("a2")
                .validateAndApply("f1")
                .validateAndApply("g1")
                .validateAndApply("d1")
                .validateAndApply("c1")
                .validateAndApply("a1")
                .validateAndApply("b1")
                .validateAndApply("c2")
                .validateAndApply("a7");

        OthelloGame game = new OthelloGame(board);

        MockWriter output = new MockWriter();
        game.verifyCanContinueGame(output);
        assertThat(output.getLines(), equalTo(newArrayList(
                "No moves available for player 'X', skipping"
        )));

        output = new MockWriter();
        game.renderBoard(output);
        assertThat(output.getLines(), equalTo(newArrayList(
                "1 XOOX-XOX",
                "2 OOXXXOXX",
                "3 OOXXOX-X",
                "4 OOOXXXXX",
                "5 OOOOXXXO",
                "6 OOOOOXXO",
                "7 OOOOOOOO",
                "8 OXXXXOOO",
                "  abcdefgh",
                ""
        )));
    }
}