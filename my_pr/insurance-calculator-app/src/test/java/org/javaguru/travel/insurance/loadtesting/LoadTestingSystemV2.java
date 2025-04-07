package org.javaguru.travel.insurance.loadtesting;

import org.javaguru.travel.insurance.loadtesting.LoadTestingStatistic;

import java.util.ArrayList;
import java.util.List;

public class LoadTestingSystemV2 {

    public static void main(String[] args) {
        new LoadTestingSystemV2().executeForAMinute(5, 600);
    }

    public void executeForAMinute(int parallelThreadCount, int requestCount) {
        long intervalBetweenRequestsInMillis = 60000L / requestCount;

        LoadTestingStatistic statisticV2 = new LoadTestingStatistic();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < requestCount; i++) {
            for (int j = 0; j < parallelThreadCount; j++) {
                Thread v2Call = new Thread(new V2Call(statisticV2));
                v2Call.start();
                threads.add(v2Call);
            }
            try {
                Thread.sleep(intervalBetweenRequestsInMillis);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("V2 average = " + statisticV2.calculateAverage());
        System.out.println("V2 min = " + statisticV2.findMinTime());
        System.out.println("V2 max = " + statisticV2.findMaxTime());
        System.out.println("V2 sum time = " + statisticV2.calculateSum());
    }
}
