package com.othello;

import com.othello.writer.MockWriter;
import org.junit.Test;

import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class OthelloGameTest {
    @Test
    public void testEndStateFullBoard() {
        Board board = Board.newBoard();

        Optional<Coordinate> nextMove = board.bestNextMove();
        while (nextMove.isPresent()) {
            board = board.validateAndApply(nextMove.get().coordinate());
            nextMove = board.bestNextMove();
        }

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
}