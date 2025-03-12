package org.javaguru.travel.insurance.loadtesting;

import com.google.common.base.Stopwatch;
import org.javaguru.travel.insurance.common.JsonFileReader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

public class RestCallExample {

    public static void main(String[] args) {
        LoadTestingStatistic statisticV1 = new LoadTestingStatistic();
        LoadTestingStatistic statisticV2 = new LoadTestingStatistic();

        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Thread v1Call = new Thread(new V1Call(statisticV1));
            Thread v2Call = new Thread(new V2Call(statisticV2));
            v1Call.start();
            v2Call.start();
            threads.add(v1Call);
            threads.add(v2Call);
        }

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Average for v1 "+ statisticV1.calculateAverage());
        System.out.println("Min for v1 "+ statisticV1.findMinTime());
        System.out.println("Max for v1 "+ statisticV1.findMaxTime());

        System.out.println("Average for v2 "+ statisticV2.calculateAverage());
        System.out.println("Min for v2 "+ statisticV2.findMinTime());
        System.out.println("Max for v2 "+ statisticV2.findMaxTime());
    }
}
