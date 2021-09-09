package com.exercise.dz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Reader {
    public static final int CHUNK_SIZE = 1000;
    private final Scanner inputScanner;
    ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public Reader(Scanner inputScanner) {
        this.inputScanner = inputScanner;
    }

    public void read() {
        int lineNum = 0;
        List<MatchRequest> chunk = new ArrayList<>(CHUNK_SIZE);

        while (inputScanner.hasNext()) {
           chunk.add(new MatchRequest(inputScanner.nextLine(), lineNum++));
            if (lineNum % CHUNK_SIZE == 0) {
                // process chunk
                for (MatchRequest matchRequest : chunk) {
                    threadPool.submit(new Matcher(matchRequest));
                }
                chunk.clear();
            }
        }

        // the last chunk
        if (!chunk.isEmpty()) {
            // process chunk
            for (MatchRequest matchRequest : chunk) {
                threadPool.submit(new Matcher(matchRequest));
            }
        }
         // "Initiates an orderly shutdown in which previously submitted
         // tasks are executed, but no new tasks will be accepted."
         threadPool.shutdown();
    }
}
