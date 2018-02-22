package com.othello;

import com.othello.exceptions.NonPermittedMoveException;
import com.othello.writer.OutputWriter;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.google.common.collect.Maps.newHashMap;
import static com.othello.Coordinate.*;

public class Board {
    private static final String EMPTY_GRID_CELL = "-";

    public Player currentPlayer;
    private final Map<Coordinate, Player> positions;

    private Board(Player currentPlayer, Map<Coordinate, Player> positions) {
        this.currentPlayer = currentPlayer;
        this.positions = positions;
    }

    public static Board newBoard() {
        return new Board(Player.X, newHashMap())
                .applyWithoutQuestionOrCalculation("e4")
                .applyWithoutQuestionOrCalculation("d4")
                .applyWithoutQuestionOrCalculation("d5")
                .applyWithoutQuestionOrCalculation("e5");
    }

    private Board applyWithoutQuestionOrCalculation(String coordinateString) {
        Coordinate coordinate = generate(coordinateString);
        Map<Coordinate, Player> newPositions = newHashMap(positions);
        newPositions.put(coordinate, currentPlayer);
        return new Board(Player.opponentOf(currentPlayer), newPositions);
    }

//    TODO probably shouldn't have the writer this deep
    public void render(OutputWriter output) {
        for (String y : YAXIS) {
            StringBuilder builder = new StringBuilder();
            builder.append(y).append(" ");
            for (String x : XAXIS) {
                Coordinate coordinate = generate(x + y);
                builder.append(positions.containsKey(coordinate) ?
                        positions.get(coordinate) :
                        EMPTY_GRID_CELL);
            }
            output.writeLine(builder.toString());
        }
        output.writeLine("  " + String.join("", XAXIS));
        output.writeLine("");
    }

    public Board validateAndApply(String coordinateString) {
        Coordinate coordinate = generate(coordinateString);
        Player opponent = Player.opponentOf(currentPlayer);

        if (positions.containsKey(coordinate)) throw NonPermittedMoveException.alreadyTaken(coordinate);

        List<Coordinate> touchingCoordinates = touchingCoordinates(coordinate);
        List<Coordinate> coordinatesWhichFinallyReachPlayerCoordinates = validEndCoordinates(coordinate, opponent, touchingCoordinates);

        Map<Coordinate, Player> newPositionList = newHashMap(positions);
        newPositionList.put(coordinate, currentPlayer);
        for (Coordinate coord : coordinatesWhichFinallyReachPlayerCoordinates) {
            boolean reachedPlayer = false;
            for (Coordinate lineCoord : followTheLine(coordinate, coord)) {
                if (newPositionList.containsKey(lineCoord) && newPositionList.get(lineCoord) == currentPlayer)
                    reachedPlayer = true;
                if (!reachedPlayer) newPositionList.put(lineCoord, currentPlayer);
            }
        }

        return new Board(opponent, newPositionList);
    }

    private List<Coordinate> validEndCoordinates(Coordinate coordinate, Player opponent, List<Coordinate> actualAdjacentCoordinates) {
        List<Coordinate> adjacentOpponentCoordinates = actualAdjacentCoordinates.stream().filter(c -> positions.get(c) == opponent).collect(Collectors.toList());
        if (adjacentOpponentCoordinates.size() < 1) throw NonPermittedMoveException.notPermissableForPlayer(coordinate);

        List<Coordinate> coordinatesThatSurroundOpponent = adjacentOpponentCoordinates.stream()
                .filter(coordinatesThatSurroundOpponent(coordinate))
                .collect(Collectors.toList());

        if (coordinatesThatSurroundOpponent.size() < 1)
            throw NonPermittedMoveException.notPermissableForPlayer(coordinate);

        return coordinatesThatSurroundOpponent;
    }

    private Predicate<Coordinate> coordinatesThatSurroundOpponent(Coordinate startCoordinate) {
        return nextCoordinate -> {
            List<Coordinate> restOfLine = followTheLine(startCoordinate, nextCoordinate);
            AtomicBoolean passedEmptyCell = new AtomicBoolean(false);
            return restOfLine.stream().anyMatch(line -> {
                if (!positions.containsKey(line)) passedEmptyCell.set(true);
                return !passedEmptyCell.get() && positions.get(line) == currentPlayer;
            });
        };
    }

    private List<Coordinate> touchingCoordinates(Coordinate coordinate) {
        List<Coordinate> adjacentCoordinates = adjacentCoordinates(coordinate);
        List<Coordinate> actualAdjacentCoordinates = adjacentCoordinates.stream().filter(positions::containsKey).collect(Collectors.toList());
        if (actualAdjacentCoordinates.size() < 1) throw NonPermittedMoveException.notAdjacent(coordinate);
        return actualAdjacentCoordinates;
    }

    public long pointsFor(Player player) {
        return positions.values().stream().filter(s -> s == player).count();
    }

    public Optional<Coordinate> bestNextMove() {
        Map<Long, Coordinate> possibleMoves = newHashMap();

        for (String y : YAXIS) {
            for (String x : XAXIS) {
                Coordinate coordinate = generate(x + y);
                try {
                    possibleMoves.put(validateAndApply(coordinate.coordinate()).pointsFor(currentPlayer), coordinate);
                } catch (Exception ignored) {
                }
            }
        }
        if (possibleMoves.size() < 1) return Optional.empty();
        return Optional.of(possibleMoves.get(Collections.max(possibleMoves.keySet())));
    }

    public Board skipMove() {
        return new Board(Player.opponentOf(currentPlayer), positions);
    }
}
