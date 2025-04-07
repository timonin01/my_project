package org.javaguru.travel.insurance.rest.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.common.JsonFileReader;
import org.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import org.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelGetAgreementRestControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private JsonFileReader jsonFileReader;

    @Autowired private MockMvc mockMvc;

    private static final String BASE_URL = "/insurance/travel/api/internal/agreement/";

//    @Test
//    @DisplayName("correct uuid")
//    public void test_case_1()  throws Exception{
//        String uuid = calculateAgreementAndGetUuid();
//        comparingJSON(uuid,"rest/internal/test_case_1/response.json",true);
//    }

    @Test
    @DisplayName("incorrect uuid")
    public void test_case_2()  throws Exception{
        comparingJSON("INCORRECT_UUID","rest/internal/test_case_2/response.json",true);
    }




    public void comparingJSON(String uuid, String responsePath,
                              boolean ignoreUUIDValue) throws Exception {
        MvcResult result = mockMvc.perform(get(BASE_URL + uuid)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        String jsonResponse = jsonFileReader.readJsonFromFile(responsePath);

        if (ignoreUUIDValue) {
            assertJson(responseBodyContent)
                    .where()
                    .keysInAnyOrder()
                    .arrayInAnyOrder()
                    .at("/uuid").isNotEmpty()
                    .isEqualTo(jsonResponse);
        } else {
            assertJson(responseBodyContent)
                    .where()
                    .keysInAnyOrder()
                    .arrayInAnyOrder()
                    .isEqualTo(jsonResponse);
        }
    }

    private String calculateAgreementAndGetUuid() throws Exception {
        String jsonRequest = jsonFileReader.readJsonFromFile("rest/internal/test_case_1/request.json");

        MvcResult result = mockMvc.perform(post("/insurance/travel/api/v2/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        TravelCalculatePremiumResponseV2 response = new ObjectMapper().readValue(responseBodyContent, TravelCalculatePremiumResponseV2.class);

        return response.getUuid();
    }

}
