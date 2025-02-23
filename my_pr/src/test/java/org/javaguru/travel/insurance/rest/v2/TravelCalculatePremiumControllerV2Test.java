package org.javaguru.travel.insurance.rest.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.common.JsonFileReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerV2Test {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private JsonFileReader jsonFileReader;

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/insurance/travel/api/v2/";

    @Test
    @DisplayName("all fields okay")
    public void test_case_1() throws Exception{
        comparingJSON("rest/v2/test_case_1/request.json","rest/v2/test_case_1/response.json");
    }

    @Test
    @DisplayName("all fields null")
    public void test_case_2() throws Exception{
        comparingJSON("rest/v2/test_case_2/request.json","rest/v2/test_case_2/response.json");
    }

    @Test
    @DisplayName("agreementDateFrom is null")
    public void test_case_3() throws Exception{
        comparingJSON("rest/v2/test_case_3/request.json","rest/v2/test_case_3/response.json");
    }

    @Test
    @DisplayName("agreementDateTo is null")
    public void test_case_4() throws Exception{
        comparingJSON("rest/v2/test_case_4/request.json","rest/v2/test_case_4/response.json");
    }

    @Test
    @DisplayName("agreementDateFrom after then agreementDateTo")
    public void test_case_5() throws Exception{
        comparingJSON("rest/v2/test_case_5/request.json","rest/v2/test_case_5/response.json");
    }

    @Test
    @DisplayName("agreementDateFrom must be in future")
    public void test_case_6() throws Exception{
        comparingJSON("rest/v2/test_case_6/request.json","rest/v2/test_case_6/response.json");
    }

    @Test
    @DisplayName("agreementDateTo must be in future")
    public void test_case_7() throws Exception{
        comparingJSON("rest/v2/test_case_7/request.json","rest/v2/test_case_7/response.json");
    }

    @Test
    @DisplayName("country is null")
    public void test_case_8() throws Exception{
        comparingJSON("rest/v2/test_case_8/request.json","rest/v2/test_case_8/response.json");
    }

    @Test
    @DisplayName("country is blank")
    public void test_case_9() throws Exception{
        comparingJSON("rest/v2/test_case_9/request.json","rest/v2/test_case_9/response.json");
    }

    @Test
    @DisplayName("medicalRiskLimitLevel is null")
    public void test_case_10() throws Exception{
        comparingJSON("rest/v2/test_case_10/request.json","rest/v2/test_case_10/response.json");
    }

    @Test
    @DisplayName("medicalRiskLimitLevel is blank")
    public void test_case_11() throws Exception{
        comparingJSON("rest/v2/test_case_11/request.json","rest/v2/test_case_11/response.json");
    }

    @Test
    @DisplayName("selected_risks is null")
    public void test_case_12() throws Exception{
        comparingJSON("rest/v2/test_case_12/request.json","rest/v2/test_case_12/response.json");
    }

    @Test
    @DisplayName("selected_risks is empty")
    public void test_case_13() throws Exception{
        comparingJSON("rest/v2/test_case_13/request.json","rest/v2/test_case_13/response.json");
    }

    @Test
    @DisplayName("personFirstName is null")
    public void test_case_14() throws Exception{
        comparingJSON("rest/v2/test_case_14/request.json","rest/v2/test_case_14/response.json");
    }

    @Test
    @DisplayName("personLastName is empty")
    public void test_case_15() throws Exception{
        comparingJSON("rest/v2/test_case_15/request.json","rest/v2/test_case_15/response.json");
    }

    @Test
    @DisplayName("personBirthDate is empty")
    public void test_case_16() throws Exception{
        comparingJSON("rest/v2/test_case_16/request.json","rest/v2/test_case_16/response.json");
    }

    @Test
    @DisplayName("personBirthDate must be in past")
    public void test_case_17() throws Exception{
        comparingJSON("rest/v2/test_case_17/request.json","rest/v2/test_case_17/response.json");
    }

    @Test
    @DisplayName("personBirthDate must be in past with two people")
    public void test_case_18() throws Exception{
        comparingJSON("rest/v2/test_case_18/request.json","rest/v2/test_case_18/response.json");
    }

    @Test
    @DisplayName("selected_risks is NOT_SUPPORTED")
    public void test_case_19() throws Exception{
        comparingJSON("rest/v2/test_case_19/request.json","rest/v2/test_case_19/response.json");
    }

    @Test
    @DisplayName("selected_risks is NOT_SUPPORTED twice")
    public void test_case_20() throws Exception{
        comparingJSON("rest/v2/test_case_20/request.json","rest/v2/test_case_20/response.json");
    }

    @Test
    @DisplayName("country is NOT_SUPPORTED ")
    public void test_case_21() throws Exception{
        comparingJSON("rest/v2/test_case_21/request.json","rest/v2/test_case_21/response.json");
    }

    @Test
    @DisplayName("medicalRiskLimitLevel is NOT_SUPPORTED")
    public void test_case_22() throws Exception{
        comparingJSON("rest/v2/test_case_22/request.json","rest/v2/test_case_22/response.json");
    }

    @Test
    @DisplayName("medicalRiskLimitLevel is NOT_SUPPORTED twice")
    public void test_case_23() throws Exception{
        comparingJSON("rest/v2/test_case_23/request.json","rest/v2/test_case_23/response.json");
    }



    public void comparingJSON(String path1,String path2) throws Exception{
        MvcResult result =mockMvc.perform(post(BASE_URL)
                        .content(jsonFileReader.readJsonFromFile(path1))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String request = result.getResponse().getContentAsString();

        String response = jsonFileReader.readJsonFromFile(path2);
        //порядок не важен
        JSONAssert.assertEquals(response, request, JSONCompareMode.NON_EXTENSIBLE);
    }


}