package com.othello;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class OthelloGameTest {
//    @Test
//    public void testPlayerOWins() {
//        Board board = Board.newBoard()
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("")
//                .validateAndApply("");
//
//        OthelloGame game = new OthelloGame(board);
//
//        MockWriter output = new MockWriter();
//        game.verifyCanContinueGame(output);
//        assertThat(output.getLines(), equalTo(newArrayList(
//                "No moves available for player 'X', skipping",
//                "No further moves possible. Game over!",
//                "Player O wins! ( 45 vs 19 )"
//        )));
//
//        output = new MockWriter();
//        game.renderBoard(output);
//        assertThat(output.getLines(), equalTo(newArrayList(
//                "1 XXOOOOOO",
//                "2 XXOOOOOO",
//                "3 XXXXOOOO",
//                "4 XXOOOOOO",
//                "5 XXOOOXOO",
//                "6 XXOOXOOO",
//                "7 XXOOOOOO",
//                "8 XOOOOOOO",
//                "  abcdefgh",
//                ""
//        )));
//    }

    @Test
    public void gameCanSkipPlayerMove() {
        OthelloGame game = new OthelloGame(Board.newBoard());

        assertThat(game.currentPlayer(), equalTo(Player.X));

        game.skipMove();
        assertThat(game.currentPlayer(), equalTo(Player.O));
    }
}