package org.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private JsonFileReader jsonFileReader;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Test case 1: firstName does not exist")
    public void firstNameDoesNotExist()throws Exception{
        comparingJSON( "TravelCalculatePremiumRequest_firstname_does_not_exist.json",
                "TravelCalculatePremiumResponse_firstname_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 2: lastName does not exist")
    public void lastNameDoesNotExist()throws Exception{
        comparingJSON( "TravelCalculatePremiumRequest_lastname_does_not_exist.json",
                "TravelCalculatePremiumResponse_lastname_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 3: agreementDateFrom does not exist")
    public void agreementDateFromDoesNotExist()throws Exception{
        comparingJSON( "TravelCalculatePremiumRequest_agreementDateFrom_does_not_exist.json",
                "TravelCalculatePremiumResponse_agreementDateFrom_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 4: agreementDateTo does not exist")
    public void agreementDateToDoesNotExist()throws Exception{
        comparingJSON( "TravelCalculatePremiumRequest_agreementDateTo_does_not_exist.json",
                "TravelCalculatePremiumResponse_agreementDateTo_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 5: all fields does not exist")
    public void allFieldsDoesNotExist()throws Exception{
        comparingJSON( "TravelCalculatePremiumRequest_all_fields_does_not_exist.json",
                "TravelCalculatePremiumResponse_all_fields_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 6: agreementDateTo must be after then agreement agreementDateFrom")
    public void agreementDateToMustBeAfterThenAgreementDateFrom()throws Exception{
        comparingJSON( "TravelCalculatePremiumRequest_agreementDateFrom_must_be_after_then_agreementDateTo.json",
                "TravelCalculatePremiumResponse_agreementDateFrom_must_be_after_then_agreementDateTo.json");
    }

    @Test
    @DisplayName("Test case 7: selected_risks is null")
    public void selectedRisksIsNull()throws Exception{
        comparingJSON("TravelCalculatePremiumRequest_selected_risks_is_null.json",
                "TravelCalculatePremiumResponse_selected_risks_is_null.json");
    }

    @Test
    @DisplayName("Test case 8: selected_risks is empty")
    public void selectedRisksIsEmpty()throws Exception{
        comparingJSON("TravelCalculatePremiumRequest_selected_risks_is_empty.json",
                "TravelCalculatePremiumResponse_selected_risks_is_empty.json");
    }

    @Test
    @DisplayName("Test case 9: all are okay")
    public void allFieldsAreCorrect()throws Exception{
        comparingJSON( "TravelCalculatePremiumRequest_all_fields_are_okay.json",
                "TravelCalculatePremiumResponse_all_fields_are_okay.json");
    }

    public void comparingJSON(String path1,String path2) throws Exception{
        MvcResult result =mockMvc.perform(post("/insurance/travel/")
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