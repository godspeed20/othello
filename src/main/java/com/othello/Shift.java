package com.othello;

import com.othello.exceptions.TooBigAShiftException;

public class Shift {
    public final int xShift;
    public final int yShift;

    private Shift(int xShift, int yShift) {
        this.yShift = yShift;
        this.xShift = xShift;
    }

    public static Shift generate(int initialX, int subsequentX, int initialY, int subsequentY) {
        Integer xShift = subsequentX - initialX;
        Integer yShift = subsequentY - initialY;
        if (Math.abs(xShift) > 1 || Math.abs(yShift) > 1) throw new TooBigAShiftException();
        return new Shift(xShift, yShift);
    }
}
