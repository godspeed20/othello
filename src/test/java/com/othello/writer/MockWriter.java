package com.othello.writer;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class MockWriter extends OutputWriter {
    private List<String> lines = newArrayList();

    public void write(String line) {
        writeLine(line);
    }

    public void writeLine(String line) {
        lines.add(line);
    }

    public List<String> getLines() {
        return lines;
    }
}
