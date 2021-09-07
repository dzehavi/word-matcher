package com.exercise.dz;

public class Match {
     final String name;
     final int lineOffset;
     final int charOffset;

    public Match(String name, int lineOffset, int charOffset) {
        this.name = name;
        this.lineOffset = lineOffset;
        this.charOffset = charOffset;
    }

    @Override
    public String toString() {
        return "[lineOffset=" + lineOffset + ". charOffset=" + charOffset + "]";
    }
}
