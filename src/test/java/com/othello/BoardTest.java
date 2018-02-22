package com.othello;

import com.othello.exceptions.InvalidCoordinateException;
import com.othello.exceptions.NonPermittedMoveException;
import com.othello.writer.MockWriter;
import org.junit.Test;

import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class BoardTest {
    private static void assertCanBePlayedByPlayer(String position) {
        Board board = Board.newBoard();
        Board newBoard = board.validateAndApply(position);

        assertThat(newBoard.currentPlayer, equalTo(Player.O));
        assertThat(newBoard.pointsFor(Player.X), equalTo(4L));
        assertThat(newBoard.pointsFor(Player.O), equalTo(1L));
    }

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
    public void canAskForHint() {
        Board board = Board.newBoard();

        Optional<Coordinate> bestNextMove = board.bestNextMove();

        assertThat(bestNextMove.isPresent(), equalTo(true));
        assertThat(bestNextMove.get(), equalTo(Coordinate.generate("e6")));
    }

    @Test
    public void multipleHintsReturnSameValue() {
        Board board = Board.newBoard();
        assertThat(board.bestNextMove().get(), equalTo(board.bestNextMove().get()));
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
}