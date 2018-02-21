package com.othello;

import com.othello.exceptions.InvalidCoordinateException;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class CoordinateTest {
    @Test
    public void failsWhenCoordinateTooShort() {
        try {
            Coordinate.generate("A");
            fail("coordinate is invalid, should have failed");
        } catch (InvalidCoordinateException e) {
            assertThat(e.getMessage(), equalTo("Coordinate A is invalid, should be a valid board reference"));
        }
    }

    @Test
    public void failsWhenCoordinateTooLong() {
        try {
            Coordinate.generate("AAA");
            fail("coordinate is invalid, should have failed");
        } catch (InvalidCoordinateException e) {
            assertThat(e.getMessage(), equalTo("Coordinate AAA is invalid, should be a valid board reference"));
        }
    }

    @Test
    public void failsWhenCoordinateIsAllAlpha() {
        try {
            Coordinate.generate("AA");
            fail("coordinate is invalid, should have failed");
        } catch (InvalidCoordinateException e) {
            assertThat(e.getMessage(), equalTo("Coordinate AA is invalid, should be a valid board reference"));
        }
    }

    @Test
    public void failsWhenCoordinateIsAllNumeric() {
        try {
            Coordinate.generate("11");
            fail("coordinate is invalid, should have failed");
        } catch (InvalidCoordinateException e) {
            assertThat(e.getMessage(), equalTo("Coordinate 11 is invalid, should be a valid board reference"));
        }
    }

    @Test
    public void cannotGenerateCoordinateThatIsOutOfBounds() {
        assertInvalidBounds(-1, -1);
        assertInvalidBounds(-1, 0);
        assertInvalidBounds(0, -1);
        assertInvalidBounds(0, 8);
        assertInvalidBounds(8, 0);
        assertInvalidBounds(8, 8);
    }

    private void assertInvalidBounds(int xValue, int yValue) {
        try {
            Coordinate.generate(xValue, yValue);
            fail("coordinate is invalid, should have failed");
        } catch (InvalidCoordinateException e) {
            assertThat(e.getMessage(), equalTo("Coordinate could not be calculated, most likely impossible, should be a valid board reference"));
        }
    }

    @Test
    public void succeedsWhenCoordinateIsValid() throws InvalidCoordinateException {
        Coordinate.generate("a1");
        Coordinate.generate("A1");
        Coordinate.generate("1a");
        Coordinate.generate("1A");
    }

    @Test
    public void coordinatesMatchRegardlessOfInputDirection() throws InvalidCoordinateException {
        Coordinate alphaNumber = Coordinate.generate("a1");
        Coordinate numberAlpha = Coordinate.generate("1a");

        assertThat(alphaNumber.x, equalTo(numberAlpha.x));
        assertThat(alphaNumber.y, equalTo(numberAlpha.y));
    }

    @Test
    public void coordinatesMatchRegardlessOfInputDirectionRandomPosition() throws InvalidCoordinateException {
        Coordinate alphaNumber = Coordinate.generate("5f");
        Coordinate numberAlpha = Coordinate.generate("f5");

        assertThat(alphaNumber.x, equalTo(numberAlpha.x));
        assertThat(alphaNumber.y, equalTo(numberAlpha.y));
    }

    @Test
    public void canCalculateAdjacentCoordinates() throws InvalidCoordinateException {
        List<Coordinate> adjacentCoordinates = Coordinate.adjacentCoordinates(Coordinate.generate("c2"));

        assertThat(adjacentCoordinates, equalTo(newArrayList(
                Coordinate.generate("b1"),
                Coordinate.generate("c1"),
                Coordinate.generate("d1"),
                Coordinate.generate("b2"),
                Coordinate.generate("d2"),
                Coordinate.generate("b3"),
                Coordinate.generate("c3"),
                Coordinate.generate("d3")
        )));
    }

    @Test
    public void canCalculateAdjacentCoordinatesForUpperCaseCoordinate() throws InvalidCoordinateException {
        List<Coordinate> adjacentCoordinates = Coordinate.adjacentCoordinates(Coordinate.generate("C2"));

        assertThat(adjacentCoordinates, equalTo(newArrayList(
                Coordinate.generate("b1"),
                Coordinate.generate("c1"),
                Coordinate.generate("d1"),
                Coordinate.generate("b2"),
                Coordinate.generate("d2"),
                Coordinate.generate("b3"),
                Coordinate.generate("c3"),
                Coordinate.generate("d3")
        )));
    }

    @Test
    public void canCalculateAdjacentCoordinatesWhenInTopLeftCorner() throws InvalidCoordinateException {
        List<Coordinate> adjacentCoordinates = Coordinate.adjacentCoordinates(Coordinate.generate("a1"));

        assertThat(adjacentCoordinates, equalTo(newArrayList(
                Coordinate.generate("b1"),
                Coordinate.generate("a2"),
                Coordinate.generate("b2")
        )));
    }

    @Test
    public void canCalculateAdjacentCoordinatesWhenInTopRightCorner() throws InvalidCoordinateException {
        List<Coordinate> adjacentCoordinates = Coordinate.adjacentCoordinates(Coordinate.generate("h1"));

        assertThat(adjacentCoordinates, equalTo(newArrayList(
                Coordinate.generate("g1"),
                Coordinate.generate("g2"),
                Coordinate.generate("h2")
        )));
    }

    @Test
    public void canCalculateAdjacentCoordinatesWhenInBottomLeftCorner() throws InvalidCoordinateException {
        List<Coordinate> adjacentCoordinates = Coordinate.adjacentCoordinates(Coordinate.generate("a8"));

        assertThat(adjacentCoordinates, equalTo(newArrayList(
                Coordinate.generate("a7"),
                Coordinate.generate("b7"),
                Coordinate.generate("b8")
        )));
    }

    @Test
    public void canCalculateAdjacentCoordinatesWhenInBottomRightCorner() throws InvalidCoordinateException {
        List<Coordinate> adjacentCoordinates = Coordinate.adjacentCoordinates(Coordinate.generate("h8"));

        assertThat(adjacentCoordinates, equalTo(newArrayList(
                Coordinate.generate("g7"),
                Coordinate.generate("h7"),
                Coordinate.generate("g8")
        )));
    }

    @Test
    public void canCalculateTheRestOfTheLineHorizontalEast() throws InvalidCoordinateException {
        List<Coordinate> adjacentCoordinates = Coordinate.followTheLine(Coordinate.generate("a1"), Coordinate.generate("b1"));

        assertThat(adjacentCoordinates, equalTo(newArrayList(
                Coordinate.generate("b1"),
                Coordinate.generate("c1"),
                Coordinate.generate("d1"),
                Coordinate.generate("e1"),
                Coordinate.generate("f1"),
                Coordinate.generate("g1"),
                Coordinate.generate("h1")
        )));
    }

    @Test
    public void canCalculateTheRestOfTheLineDiagonalSouthEast() throws InvalidCoordinateException {
        List<Coordinate> adjacentCoordinates = Coordinate.followTheLine(Coordinate.generate("a1"), Coordinate.generate("b2"));

        assertThat(adjacentCoordinates, equalTo(newArrayList(
                Coordinate.generate("b2"),
                Coordinate.generate("c3"),
                Coordinate.generate("d4"),
                Coordinate.generate("e5"),
                Coordinate.generate("f6"),
                Coordinate.generate("g7"),
                Coordinate.generate("h8")
        )));
    }

    @Test
    public void canCalculateTheRestOfTheLineVerticalSouth() throws InvalidCoordinateException {
        List<Coordinate> adjacentCoordinates = Coordinate.followTheLine(Coordinate.generate("a1"), Coordinate.generate("a2"));

        assertThat(adjacentCoordinates, equalTo(newArrayList(
                Coordinate.generate("a2"),
                Coordinate.generate("a3"),
                Coordinate.generate("a4"),
                Coordinate.generate("a5"),
                Coordinate.generate("a6"),
                Coordinate.generate("a7"),
                Coordinate.generate("a8")
        )));
    }
}