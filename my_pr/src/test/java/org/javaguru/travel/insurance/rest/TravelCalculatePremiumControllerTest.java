package org.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.Matchers.hasSize;
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
        comparingJSON("rest/TravelCalculatePremiumRequest_firstname_does_not_exist.json",
                "rest/TravelCalculatePremiumResponse_firstname_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 2: lastName does not exist")
    public void lastNameDoesNotExist()throws Exception{
        comparingJSON("rest/TravelCalculatePremiumRequest_lastname_does_not_exist.json",
                "rest/TravelCalculatePremiumResponse_lastname_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 3: agreementDateFrom does not exist")
    public void agreementDateFromDoesNotExist()throws Exception{
        comparingJSON("rest/TravelCalculatePremiumRequest_agreementDateFrom_does_not_exist.json",
                "rest/TravelCalculatePremiumResponse_agreementDateFrom_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 4: agreementDateTo does not exist")
    public void agreementDateToDoesNotExist()throws Exception{
        comparingJSON("rest/TravelCalculatePremiumRequest_agreementDateTo_does_not_exist.json",
                "rest/TravelCalculatePremiumResponse_agreementDateTo_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 5: all fields does not exist")
    public void allFieldsDoesNotExist()throws Exception{
        comparingJSON("rest/TravelCalculatePremiumRequest_all_fields_does_not_exist.json",
                "rest/TravelCalculatePremiumResponse_all_fields_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 6: agreementDateTo must be after then agreement agreementDateFrom")
    public void agreementDateToMustBeAfterThenAgreementDateFrom()throws Exception{
        comparingJSON("rest/TravelCalculatePremiumRequest_agreementDateFrom_must_be_after_then_agreementDateTo.json",
                "rest/TravelCalculatePremiumResponse_agreementDateFrom_must_be_after_then_agreementDateTo.json");
    }

    @Test
    @DisplayName("Test case 7: country is null")
    public void countryIsNull() throws Exception{
        comparingJSON("rest/TravelCalculatePremiumRequest_country_does_not_exist.json",
                "rest/TravelCalculatePremiumResponse_country_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 8: selected_risks is null")
    public void selectedRisksIsNull()throws Exception{
        comparingJSON("rest/TravelCalculatePremiumRequest_selected_risks_is_null.json",
                "rest/TravelCalculatePremiumResponse_selected_risks_is_null.json");
    }

    @Test
    @DisplayName("Test case 9: selected_risks is empty")
    public void selectedRisksIsEmpty()throws Exception{
        comparingJSON("rest/TravelCalculatePremiumRequest_selected_risks_is_empty.json",
                "rest/TravelCalculatePremiumResponse_selected_risks_is_empty.json");
    }

    @Test
    @DisplayName("Test case 10: selected_risks are wrong")
    public void selectedRisksAreWrong() throws Exception{
        comparingJSON("rest/TravelCalculatePremiumRequest_selected_risks_is_wrong.json",
                "rest/TravelCalculatePremiumResponse_selected_risks_is_wrong.json");
    }

    @DisplayName("Test case 11: personBirthdayDate is null")
    public void personBirthdayDateIsNull() throws Exception{
        comparingJSON("rest/TravelCalculatePremiumRequest_personBirthdayDate_does_not_exist.json",
                "rest/TravelCalculatePremiumResponse_personBirthdayDate_does_not_exist.json");
    }

    @DisplayName("Test case 12: personBirthdayDate in future")
    public void personBirthdayDateInFuture() throws Exception{
        comparingJSON("rest/TravelCalculatePremiumRequest_personBirthdayDate_in_future.json",
                "rest/TravelCalculatePremiumResponse_personBirthdayDate_in_future.json");
    }

    @DisplayName("Test case 13: all are okay")
    public void allFieldsAreCorrect()throws Exception{
        comparingJSON("rest/TravelCalculatePremiumRequest_all_fields_are_okay.json",
                "rest/TravelCalculatePremiumResponse_all_fields_are_okay.json");
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