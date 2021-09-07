package com.exercise.dz;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {

    static final List<String> NAMES = Arrays.asList("James","John","Robert","Michael","William","David","Richard","Charles",
            "Joseph","Thomas","Christopher","Daniel","Paul","Mark","Donal d","George","Kenneth","Steven","Edward",
            "Brian","Ronald","Anthony","Kevin","Jason","Matthew","Gary","Timothy","Jose","Larry","Jeffrey",
            "Frank","Scott","Eric","Stephen","Andrew","Raymond","Gregory","Joshua","Jerry","Dennis","Walter",
            "Patrick","Peter","Harold","Douglas","Henry","Carl","Arthur","Ryan","Roger");

    static Queue<Match> RESPONSE_QUEUE = new LinkedBlockingDeque<>();
    static List<MatchRequest> REQUESTS_IN_PROCESS = new Vector<>(10);

    public static void main(String[] args) throws IOException, InterruptedException {

        Aggregator aggregator = new Aggregator();
        Thread aggregatorThread = new Thread(aggregator);
        aggregatorThread.start();

        //Scanner inputScanner = new Scanner(Paths.get("src/test/resources/test.txt"));
        Scanner inputScanner = new Scanner(new URL("http://norvig.com/big.txt").openStream());

        Generator generator = new Generator(inputScanner);
        generator.generate();

        // Aggregator will first empty the responses, and then terminate
        aggregator.setWaitAndTerminate();
        // wait for aggregator thread to finish
        aggregatorThread.join();
        // now print the result
        aggregator.print();
    }
}
