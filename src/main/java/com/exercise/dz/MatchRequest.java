package com.exercise.dz;

public class MatchRequest {
    final String line;
    final int lineNum;

    public MatchRequest(String line, int lineNum) {
        this.line = line;
        this.lineNum = lineNum;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof  MatchRequest))
            return false;
        MatchRequest other = (MatchRequest) obj;
        return other.lineNum == lineNum && other.line.equals(line);
    }
}
