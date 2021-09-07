package com.exercise.dz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aggregator implements Runnable{
    private final Map<String, List<Match>> nameToMatches = new HashMap<>();
    private boolean waitAndTerminate;

    void print() {
        for (String name: nameToMatches.keySet()) {
            System.out.print(name + " --> ");
            System.out.println(nameToMatches.get(name));
        }
    }

    @Override
    public void run() {
        // Aggregator waits for no more match requests & responses
        while (!(waitAndTerminate && Main.RESPONSE_QUEUE.isEmpty() && Main.REQUESTS_IN_PROCESS.isEmpty())) {
            Match match = Main.RESPONSE_QUEUE.peek();
            // maybe response queue is empty, but some Matchers still work.
            if (match != null) {
                match = Main.RESPONSE_QUEUE.remove();
                List<Match> matches = nameToMatches.computeIfAbsent(match.name, k -> new ArrayList<>());
                matches.add(match);
            }
        }
    }

    public void setWaitAndTerminate() {
        this.waitAndTerminate = true;
    }
}
