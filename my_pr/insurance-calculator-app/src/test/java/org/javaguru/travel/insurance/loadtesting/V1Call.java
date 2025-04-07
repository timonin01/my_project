package org.javaguru.travel.insurance.loadtesting;

import com.google.common.base.Stopwatch;
import org.javaguru.travel.insurance.common.JsonFileReader;
import org.javaguru.travel.insurance.loadtesting.CommonCall;
import org.javaguru.travel.insurance.loadtesting.LoadTestingStatistic;

public class V1Call extends CommonCall implements Runnable {

    private static final String BASE_URL_V1 = "http://localhost:8080/insurance/travel/api/v1/";
    private JsonFileReader jsonFileReader = new JsonFileReader();
    private LoadTestingStatistic statistic;

    public V1Call(LoadTestingStatistic statistic) {
        this.statistic = statistic;
    }

    @Override
    public void run() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        String requestFilePath = "rest/v1/agreement/All_Fields_Are_Okay/request.json";
        String requestJson = jsonFileReader.readJsonFromFile(requestFilePath);

        String responseFilePath = "rest/v1/agreement/All_Fields_Are_Okay/response.json";
        String responseJson = jsonFileReader.readJsonFromFile(responseFilePath);

        executeRestCallAndCompareResults(requestJson,responseJson,BASE_URL_V1);

        stopwatch.stop();
        long time = stopwatch.elapsed().toMillis();
        System.out.println("Request v1 processing "+time);

        statistic.addTime(time);
    }

}
