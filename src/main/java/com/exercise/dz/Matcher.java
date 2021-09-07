package com.exercise.dz;

public class Matcher implements Runnable {

    private final MatchRequest matchRequest;

    public Matcher(MatchRequest matchRequest) {
        Main.REQUESTS_IN_PROCESS.add(matchRequest);
        this.matchRequest = matchRequest;
    }

    @Override
    public void run() {
        try {
            for (String name : Main.NAMES) {
                int start = 0;
                int temp;
                while ((temp = matchRequest.line.indexOf(name, start)) != -1) {
                    Main.RESPONSE_QUEUE.add(new Match(name, matchRequest.lineNum, temp));
                    start = temp + 1;
                }
            }
        } finally {
            // must remove request from process list or aggregator will continue forever.
            Main.REQUESTS_IN_PROCESS.remove(matchRequest);
        }
    }
}
