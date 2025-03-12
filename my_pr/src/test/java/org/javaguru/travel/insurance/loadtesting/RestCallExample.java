package org.javaguru.travel.insurance.loadtesting;

import com.google.common.base.Stopwatch;
import org.javaguru.travel.insurance.common.JsonFileReader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

public class RestCallExample {

    private static final String BASE_URL_V1 = "http://localhost:8080/insurance/travel/api/v1/";
    private static final String BASE_URL_V2 = "http://localhost:8080/insurance/travel/api/v2/";

    public static void main(String[] args) {
        JsonFileReader jsonFileReader = new JsonFileReader();
        executeV1Call(jsonFileReader);
        executeV2Call(jsonFileReader);
    }

    private static void executeV1Call(JsonFileReader jsonFileReader){
        Stopwatch stopwatch = Stopwatch.createStarted();
        String requestFilePath = "rest/v1/agreement/All_Fields_Are_Okay/request.json";
        String requestJson = jsonFileReader.readJsonFromFile(requestFilePath);

        String responseFilePath = "rest/v1/agreement/All_Fields_Are_Okay/response.json";
        String responseJson = jsonFileReader.readJsonFromFile(responseFilePath);

        executeRestCallAndCompareResults(requestJson,responseJson,BASE_URL_V1);

        stopwatch.stop();
        long time = stopwatch.elapsed().toMillis();
        System.out.println("Request v1 processing "+time);
    }

    private static void executeV2Call(JsonFileReader jsonFileReader){
        Stopwatch stopwatch = Stopwatch.createStarted();
        String requestFilePath = "rest/v2/agreement/All_Fields_Okay/request.json";
        String requestJson = jsonFileReader.readJsonFromFile(requestFilePath);

        String responseFilePath = "rest/v2/agreement/All_Fields_Okay/response.json";
        String responseJson = jsonFileReader.readJsonFromFile(responseFilePath);

        executeRestCallAndCompareResults(requestJson,responseJson,BASE_URL_V2);

        stopwatch.stop();
        long time = stopwatch.elapsed().toMillis();
        System.out.println("Request v2 processing "+time);
    }

    private static void executeRestCallAndCompareResults(String jsonRequest,
                                                         String jsonExpectedResponse,
                                                         String url) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequest, headers);

        String responseBodyContent = restTemplate.postForObject(url, requestEntity, String.class);

        assertJson(responseBodyContent)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .at("/uuid").isNotEmpty()
                .isEqualTo(jsonExpectedResponse);
    }
}
