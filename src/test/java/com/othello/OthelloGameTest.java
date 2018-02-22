package com.othello;

import com.othello.exceptions.PlayerCannotSkipMoveException;
import org.junit.Test;

import java.util.List;

import static com.google.inject.internal.util.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.fail;

public class OthelloGameTest {
    @Test
    public void gameCanSkipPlayerMoveWhenNoMovesAvailable() {
        OthelloGame game = new OthelloGame(Board.newBoard());

        List<String> movesTillPlayerHasNoMoveMovesLeft = newArrayList("e6", "f6", "g6", "g7", "g8", "h8", "f5", "f8", "f7", "e7", "d8", "d7", "c8", "c7", "d6", "h5", "e8", "c5", "b7", "c4", "h6", "h7", "c3", "g4", "b6", "c6", "g5", "a8", "b8", "a6", "a5", "a4", "b4", "f3", "f4", "b3", "h4", "b5", "a3", "d2", "d3", "e2", "e3", "f2", "g2", "h2", "h1", "b2", "h3", "a2", "f1", "g1", "d1", "c1", "a1", "b1", "c2", "a7");

        movesTillPlayerHasNoMoveMovesLeft.forEach(game::applyCommand);

        assertThat(game.currentPlayer(), equalTo(Player.X));
        assertThat(game.currentPlayerHasMovesAvailable(), equalTo(false));

        game.skipMove();
        assertThat(game.currentPlayer(), equalTo(Player.O));
        assertThat(game.currentPlayerHasMovesAvailable(), equalTo(true));
    }

    @Test
    public void cannotSkipMoveWhileMovesAreAvailable() {
        OthelloGame game = new OthelloGame(Board.newBoard());

        assertThat(game.currentPlayer(), equalTo(Player.X));
        assertThat(game.currentPlayerHasMovesAvailable(), equalTo(true));

        try {
            game.skipMove();
            fail("should not be able to skip move");
        } catch (PlayerCannotSkipMoveException e) {
            assertThat(e.getMessage(), equalTo("Player X has moves left, cannot skip their turn"));
        }
    }
}