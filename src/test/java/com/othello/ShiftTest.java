package com.othello;

import com.othello.exceptions.TooBigAShiftException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class ShiftTest {
    @Test
    public void createsZeroShiftWhenAxisAreTheSame() throws TooBigAShiftException {
        Shift shift = Shift.generate(0, 0, 0, 0);

        assertThat(shift.xShift, equalTo(0));
        assertThat(shift.yShift, equalTo(0));
    }

    @Test
    public void createsPositiveShiftWhenAxisIncrement() throws TooBigAShiftException {
        Shift shift = Shift.generate(0, 1, 0, 1);

        assertThat(shift.xShift, equalTo(1));
        assertThat(shift.yShift, equalTo(1));
    }

    @Test
    public void createsNegativeShiftWhenAxisDecrement() throws TooBigAShiftException {
        Shift shift = Shift.generate(1, 0, 1, 0);

        assertThat(shift.xShift, equalTo(-1));
        assertThat(shift.yShift, equalTo(-1));
    }

    @Test
    public void createsHorizontalShiftWhenAxisMovesHorizontally() throws TooBigAShiftException {
        Shift shift = Shift.generate(0, 1, 0, 0);

        assertThat(shift.xShift, equalTo(1));
        assertThat(shift.yShift, equalTo(0));
    }

    @Test
    public void createsVerticalShiftWhenAxisMovesVertically() throws TooBigAShiftException {
        Shift shift = Shift.generate(0, 0, 1, 0);

        assertThat(shift.xShift, equalTo(0));
        assertThat(shift.yShift, equalTo(-1));
    }

    @Test
    public void blowsUpIfShiftIsTooBigVertically() {
        try {
            Shift.generate(0, 0, 0, 2);
            fail("not permitted");
        } catch (TooBigAShiftException e) {
            assertThat(e.getMessage(), equalTo("Shift was too big to calculate, should be adjacent only"));
        }
    }

    @Test
    public void blowsUpIfShiftIsTooBigHorizontally() {
        try {
            Shift.generate(0, -2, 0, 0);
            fail("not permitted");
        } catch (TooBigAShiftException e) {
            assertThat(e.getMessage(), equalTo("Shift was too big to calculate, should be adjacent only"));
        }
    }
}
