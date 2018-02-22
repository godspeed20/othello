package com.othello;

import com.google.common.collect.Lists;
import com.othello.exceptions.InvalidCoordinateException;

import java.util.List;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;

public class Coordinate {
    public static final List<String> XAXIS = newArrayList("a", "b", "c", "d", "e", "f", "g", "h");
    public static final List<String> YAXIS = newArrayList("1", "2", "3", "4", "5", "6", "7", "8");

    public final int x;
    public final int y;

    private Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static Coordinate generate(int xCoordinate, int yCoordinate) {
        if (xCoordinate < 0 || xCoordinate > XAXIS.size() - 1 ||
                yCoordinate < 0 || yCoordinate > YAXIS.size() - 1)
            throw new InvalidCoordinateException();
        return new Coordinate(xCoordinate, yCoordinate);
    }

    static Coordinate generate(String coordinateString) {
        if (coordinateString.isEmpty() || coordinateString.length() != 2)
            throw new InvalidCoordinateException(coordinateString);

        int xCoordinate;
        int yCoordinate;
        String[] split = coordinateString.toLowerCase().split("");
        if (XAXIS.indexOf(split[0]) != -1) {
            xCoordinate = XAXIS.indexOf(split[0]);
            yCoordinate = YAXIS.indexOf(split[1]);
        } else {
            xCoordinate = XAXIS.indexOf(split[1]);
            yCoordinate = YAXIS.indexOf(split[0]);
        }
        try {
            return generate(xCoordinate, yCoordinate);
        } catch (InvalidCoordinateException ignored) {
//            Since we know the real coord, just use that instead
            throw new InvalidCoordinateException(coordinateString);
        }
    }

    static List<Coordinate> adjacentCoordinates(Coordinate coordinate) {
        List<Coordinate> coordinates = newArrayList();
        int xAxis = coordinate.x;
        int yAxis = coordinate.y;

        if (xAxis > 0 && yAxis > 0) coordinates.add(generate(xAxis - 1, yAxis - 1));
        if (yAxis > 0) coordinates.add(generate(xAxis, yAxis - 1));
        if (xAxis < 7 && yAxis > 0) coordinates.add(generate(xAxis + 1, yAxis - 1));
        if (xAxis > 0) coordinates.add(generate(xAxis - 1, yAxis));
        if (xAxis < 7) coordinates.add(generate(xAxis + 1, yAxis));
        if (xAxis > 0 && yAxis < 7) coordinates.add(generate(xAxis - 1, yAxis + 1));
        if (yAxis < 7) coordinates.add(generate(xAxis, yAxis + 1));
        if (xAxis < 7 && yAxis < 7) coordinates.add(generate(xAxis + 1, yAxis + 1));

        return coordinates;
    }

    static List<Coordinate> followTheLine(Coordinate initialCoordinate, Coordinate subsequentCoordinate) {
        List<Coordinate> coordinates = newArrayList(subsequentCoordinate);
        Shift coordinateShift = Shift.generate(
                initialCoordinate.x, subsequentCoordinate.x,
                initialCoordinate.y, subsequentCoordinate.y
        );

        try {
            while (true) {
                coordinates.add(Lists.reverse(coordinates).get(0).shift(coordinateShift));
            }
        } catch (InvalidCoordinateException ignored) {
//            exceeds possible values, so stop iterating
        }

        return coordinates;
    }

    private Coordinate shift(Shift shift) {
        return generate(x + shift.xShift, y + shift.yShift);
    }

    public String coordinate() {
        return XAXIS.get(x) + YAXIS.get(y);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }

    public String toString() {
        return "Coordinate{" + coordinate() + '}';
    }
}