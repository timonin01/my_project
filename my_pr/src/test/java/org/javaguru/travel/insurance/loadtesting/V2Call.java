package org.javaguru.travel.insurance.loadtesting;

import com.google.common.base.Stopwatch;
import org.javaguru.travel.insurance.common.JsonFileReader;

public class V2Call extends CommonCall implements Runnable{

    private static final String BASE_URL_V2 = "http://localhost:8080/insurance/travel/api/v2/";
    private JsonFileReader jsonFileReader = new JsonFileReader();
    private LoadTestingStatistic statistic = new LoadTestingStatistic();

    public V2Call(LoadTestingStatistic statistic){this.statistic = statistic;}

    @Override
    public void run() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        String requestFilePath = "rest/v2/agreement/All_Fields_Okay/request.json";
        String requestJson = jsonFileReader.readJsonFromFile(requestFilePath);

        String responseFilePath = "rest/v2/agreement/All_Fields_Okay/response.json";
        String responseJson = jsonFileReader.readJsonFromFile(responseFilePath);

        executeRestCallAndCompareResults(requestJson,responseJson,BASE_URL_V2);

        stopwatch.stop();
        long time = stopwatch.elapsed().toMillis();
        System.out.println("Request v2 processing "+time);

        statistic.addTime(time);
    }
}
