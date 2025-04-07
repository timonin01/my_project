package org.javaguru.travel.insurance.loadtesting;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

public class CommonCall {

    public static void executeRestCallAndCompareResults(String jsonRequest,
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
