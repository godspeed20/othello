package com.othello;

import com.othello.exceptions.InvalidCoordinateException;
import com.othello.exceptions.NonPermittedMoveException;
import com.othello.exceptions.PlayerCannotSkipMoveException;
import com.othello.writer.MockWriter;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class BoardTest {
    @Test
    public void newBoardHasDefaultPoints() {
        Board board = Board.newBoard();
        assertThat(board.pointsFor(Player.X), equalTo(2L));
        assertThat(board.pointsFor(Player.O), equalTo(2L));
    }

    @Test
    public void boardRejectsCoordinateThatIsOffBoardXAxis() {
        Board board = Board.newBoard();
        try {
            board.validateAndApply("i1");
            fail("invalid board coordinate");
        } catch (InvalidCoordinateException e) {
            assertThat(e.getMessage(), equalTo("Coordinate i1 is invalid, should be a valid board reference"));
        }
    }

    @Test
    public void boardRejectsCoordinateThatIsOverBoardYAxis() {
        Board board = Board.newBoard();
        try {
            board.validateAndApply("a9");
            fail("invalid board coordinate");
        } catch (InvalidCoordinateException e) {
            assertThat(e.getMessage(), equalTo("Coordinate a9 is invalid, should be a valid board reference"));
        }
    }

    @Test
    public void boardRejectsCoordinateThatIsUnderBoardYAxis() {
        Board board = Board.newBoard();
        try {
            board.validateAndApply("a0");
            fail("invalid board coordinate");
        } catch (InvalidCoordinateException e) {
            assertThat(e.getMessage(), equalTo("Coordinate a0 is invalid, should be a valid board reference"));
        }
    }

    @Test
    public void boardRejectsCoordinateThatAreAlreadyTaken() {
        assertAlreadyTakenPosition("d4");
        assertAlreadyTakenPosition("e4");
        assertAlreadyTakenPosition("d5");
        assertAlreadyTakenPosition("e5");
    }

    @Test
    public void boardRejectsCoordinateThatIsNotNextToExistingPositions() {
        assertNonPermittedPosition("a1");
        assertNonPermittedPosition("b1");
        assertNonPermittedPosition("c1");
        assertNonPermittedPosition("d1");
        assertNonPermittedPosition("e1");
        assertNonPermittedPosition("f1");
        assertNonPermittedPosition("g1");
        assertNonPermittedPosition("h1");
        assertNonPermittedPosition("a2");
        assertNonPermittedPosition("b2");
        assertNonPermittedPosition("c2");
        assertNonPermittedPosition("d2");
        assertNonPermittedPosition("e2");
        assertNonPermittedPosition("f2");
        assertNonPermittedPosition("g2");
        assertNonPermittedPosition("h2");
        assertNonPermittedPosition("a3");
        assertNonPermittedPosition("b3");
        assertNonPermittedPosition("g3");
        assertNonPermittedPosition("h3");
        assertNonPermittedPosition("a4");
        assertNonPermittedPosition("b4");
        assertNonPermittedPosition("g4");
        assertNonPermittedPosition("h4");
        assertNonPermittedPosition("a5");
        assertNonPermittedPosition("b5");
        assertNonPermittedPosition("g5");
        assertNonPermittedPosition("h5");
        assertNonPermittedPosition("a6");
        assertNonPermittedPosition("b6");
        assertNonPermittedPosition("g6");
        assertNonPermittedPosition("h6");
        assertNonPermittedPosition("a7");
        assertNonPermittedPosition("b7");
        assertNonPermittedPosition("c7");
        assertNonPermittedPosition("d7");
        assertNonPermittedPosition("e7");
        assertNonPermittedPosition("f7");
        assertNonPermittedPosition("g7");
        assertNonPermittedPosition("h7");
        assertNonPermittedPosition("a8");
        assertNonPermittedPosition("b8");
        assertNonPermittedPosition("c8");
        assertNonPermittedPosition("d8");
        assertNonPermittedPosition("e8");
        assertNonPermittedPosition("f8");
        assertNonPermittedPosition("g8");
        assertNonPermittedPosition("h8");
    }

    @Test
    public void boardRejectsCoordinateOnlyAdjacentToOwnPlayersPositions() {
        assertCannotBePlayedByPlayer("f3");
        assertCannotBePlayedByPlayer("c6");
    }

    @Test
    public void boardRejectsCoordinateAdjacentToOpponentsPieceButCannotBePlayed() {
        assertCannotBePlayedByPlayer("c3");
        assertCannotBePlayedByPlayer("e3");
        assertCannotBePlayedByPlayer("f4");
        assertCannotBePlayedByPlayer("c5");
        assertCannotBePlayedByPlayer("d6");
        assertCannotBePlayedByPlayer("f6");
    }

    @Test
    public void boardPermitsValidMove() {
        assertCanBePlayedByPlayer("d3");
        assertCanBePlayedByPlayer("c4");
        assertCanBePlayedByPlayer("f5");
        assertCanBePlayedByPlayer("e6");
    }

    @Test
    public void boardCanRenderItself() {
        Board board = Board.newBoard();
        MockWriter output = new MockWriter();

        board.render(output);
        assertThat(output.getLines(), equalTo(newArrayList(
                "1 --------",
                "2 --------",
                "3 --------",
                "4 ---OX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "")));
    }

    @Test
    public void boardRendersPlayerMoves() {
        Board board = Board.newBoard().validateAndApply("D3");
        MockWriter output = new MockWriter();

        board.render(output);
        assertThat(output.getLines(), equalTo(newArrayList(
                "1 --------",
                "2 --------",
                "3 ---X----",
                "4 ---XX---",
                "5 ---XO---",
                "6 --------",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "")));
    }

    @Test
    public void doesNotPermitJumpsAcrossGaps() {
        Board board = Board.newBoard()
                .validateAndApply("c4")
                .validateAndApply("c3")
                .validateAndApply("e6")
                .validateAndApply("d6")
                .validateAndApply("c6");
        MockWriter output = new MockWriter();

        board.render(output);
        assertThat(output.getLines(), equalTo(newArrayList(
                "1 --------",
                "2 --------",
                "3 --O-----",
                "4 --XOX---",
                "5 ---XX---",
                "6 --XXX---",
                "7 --------",
                "8 --------",
                "  abcdefgh",
                "")));

        try {
            board.validateAndApply("c7");
            fail("invalid position");
        } catch (NonPermittedMoveException e) {
            assertThat(e.getMessage(), equalTo("Position c7 is not permitted for you to play and so is not a valid move, please enter a coordinate permissible to you"));
        }
    }

    @Test
    public void boardIsImmutable() {
        Board board = Board.newBoard();
        assertThat(board.currentPlayer, equalTo(Player.X));
        assertThat(board.pointsFor(Player.O), equalTo(2L));
        assertThat(board.pointsFor(Player.X), equalTo(2L));

        Board newBoard = board.validateAndApply("d3");

        assertThat(board.currentPlayer, equalTo(Player.X));
        assertThat(board.pointsFor(Player.O), equalTo(2L));
        assertThat(board.pointsFor(Player.X), equalTo(2L));

        assertThat(newBoard.currentPlayer, equalTo(Player.O));
        assertThat(newBoard.pointsFor(Player.O), equalTo(1L));
        assertThat(newBoard.pointsFor(Player.X), equalTo(4L));
    }

    @Test
    public void canVerifyPlayerCanMove() {
        Board board = Board.newBoard();
        assertThat(board.currentPlayer, equalTo(Player.X));
        assertThat("Player X has moves", board.playerCanMakeAMove(), equalTo(true));

        board = board.validateAndApply("c4");
        assertThat(board.currentPlayer, equalTo(Player.O));
        assertThat("Player Y has moves", board.playerCanMakeAMove(), equalTo(true));
    }

    private static void assertNonPermittedPosition(String position) {
        Board board = Board.newBoard();
        try {
            board.validateAndApply(position);
            fail("invalid position " + position);
        } catch (NonPermittedMoveException e) {
            assertThat(e.getMessage(), equalTo("Position " + position + " is not adjacent to an existing piece and so is not a valid move, please enter a coordinate permissible to you"));
        }
    }

    private static void assertAlreadyTakenPosition(String position) {
        Board board = Board.newBoard();
        try {
            board.validateAndApply(position);
            fail("position " + position + " should be taken");
        } catch (NonPermittedMoveException e) {
            assertThat(e.getMessage(), equalTo("Position " + position + " is already taken and so is not a valid move, please enter a coordinate permissible to you"));
        }
    }

    private static void assertCannotBePlayedByPlayer(String position) {
        Board board = Board.newBoard();
        try {
            board.validateAndApply(position);
            fail("position " + position + " should not be possible");
        } catch (NonPermittedMoveException e) {
            assertThat(e.getMessage(), equalTo("Position " + position + " is not permitted for you to play and so is not a valid move, please enter a coordinate permissible to you"));
        }
    }

    @Test
    public void newBoardNextPlayerIsA() {
        Board board = Board.newBoard();
        assertThat(board.currentPlayer, equalTo(Player.X));
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

        MockWriter output = new MockWriter();
        board.render(output);
        MatcherAssert.assertThat(output.getLines(), IsEqual.equalTo(newArrayList(
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

    @Test
    public void canStatePlayerHasNoMoreMoves() {
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

        assertThat(board.currentPlayer, equalTo(Player.X));
        assertThat("Player X has moves", board.playerCanMakeAMove(), equalTo(false));

        board = board.skipMove();
        assertThat(board.currentPlayer, equalTo(Player.O));
        assertThat("Player Y has moves", board.playerCanMakeAMove(), equalTo(false));
    }

    @Test
    public void gameCanSkipPlayerMoveWhenNoMovesAvailable() {
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

        assertThat(board.currentPlayer, equalTo(Player.X));
        assertThat(board.playerCanMakeAMove(), equalTo(false));

        board = board.skipMove();
        assertThat(board.currentPlayer, equalTo(Player.O));
        assertThat(board.playerCanMakeAMove(), equalTo(true));
    }

    @Test
    public void cannotSkipMoveWhileMovesAreAvailable() {
        Board board = Board.newBoard();

        assertThat(board.currentPlayer, equalTo(Player.X));
        assertThat(board.playerCanMakeAMove(), equalTo(true));

        try {
            board.skipMove();
            fail("should not be able to skip move");
        } catch (PlayerCannotSkipMoveException e) {
            assertThat(e.getMessage(), equalTo("Player X has moves left, cannot skip their turn"));
        }
    }

    private static void assertCanBePlayedByPlayer(String position) {
        Board board = Board.newBoard();
        Board newBoard = board.validateAndApply(position);

        assertThat(newBoard.currentPlayer, equalTo(Player.O));
        assertThat(newBoard.pointsFor(Player.X), equalTo(4L));
        assertThat(newBoard.pointsFor(Player.O), equalTo(1L));
    }
}