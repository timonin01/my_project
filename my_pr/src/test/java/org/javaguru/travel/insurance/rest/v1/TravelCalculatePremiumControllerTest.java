package org.javaguru.travel.insurance.rest.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.common.JsonFileReader;
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

    private static final String BASE_URL = "/insurance/travel/api/v1/";


    @Test
    @DisplayName("Test case 1: firstName does not exist")
    public void firstNameDoesNotExist()throws Exception{
        comparingJSON("rest/v1/test_case_1/TravelCalculatePremiumRequest_firstname_does_not_exist.json",
                "rest/v1/test_case_1/TravelCalculatePremiumResponse_firstname_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 2: lastName does not exist")
    public void lastNameDoesNotExist()throws Exception{
        comparingJSON("rest/v1/test_case_2/TravelCalculatePremiumRequest_lastname_does_not_exist.json",
                "rest/v1/test_case_2/TravelCalculatePremiumResponse_lastname_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 3: agreementDateFrom does not exist")
    public void agreementDateFromDoesNotExist()throws Exception{
        comparingJSON("rest/v1/test_case_3/TravelCalculatePremiumRequest_agreementDateFrom_does_not_exist.json",
                "rest/v1/test_case_3/TravelCalculatePremiumResponse_agreementDateFrom_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 4: agreementDateTo does not exist")
    public void agreementDateToDoesNotExist()throws Exception{
        comparingJSON("rest/v1/test_case_4/TravelCalculatePremiumRequest_agreementDateTo_does_not_exist.json",
                "rest/v1/test_case_4/TravelCalculatePremiumResponse_agreementDateTo_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 5: all fields does not exist")
    public void allFieldsDoesNotExist()throws Exception{
        comparingJSON("rest/v1/test_case_5/TravelCalculatePremiumRequest_all_fields_does_not_exist.json",
                "rest/v1/test_case_5/TravelCalculatePremiumResponse_all_fields_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 6: agreementDateTo must be after then agreement agreementDateFrom")
    public void agreementDateToMustBeAfterThenAgreementDateFrom()throws Exception{
        comparingJSON("rest/v1/test_case_6/TravelCalculatePremiumRequest_agreementDateFrom_must_be_after_then_agreementDateTo.json",
                "rest/v1/test_case_6/TravelCalculatePremiumResponse_agreementDateFrom_must_be_after_then_agreementDateTo.json");
    }

    @Test
    @DisplayName("Test case 7: country is null")
    public void countryIsNull() throws Exception{
        comparingJSON("rest/v1/test_case_7/TravelCalculatePremiumRequest_country_does_not_exist.json",
                "rest/v1/test_case_7/TravelCalculatePremiumResponse_country_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 8: selected_risks is null")
    public void selectedRisksIsNull()throws Exception{
        comparingJSON("rest/v1/test_case_8/TravelCalculatePremiumRequest_selected_risks_is_null.json",
                "rest/v1/test_case_8/TravelCalculatePremiumResponse_selected_risks_is_null.json");
    }

    @Test
    @DisplayName("Test case 9: selected_risks is empty")
    public void selectedRisksIsEmpty()throws Exception{
        comparingJSON("rest/v1/test_case_9/TravelCalculatePremiumRequest_selected_risks_is_empty.json",
                "rest/v1/test_case_9/TravelCalculatePremiumResponse_selected_risks_is_empty.json");
    }

    @Test
    @DisplayName("Test case 10: selected_risks are wrong")
    public void selectedRisksAreWrong() throws Exception{
        comparingJSON("rest/v1/test_case_10/TravelCalculatePremiumRequest_selected_risks_is_wrong.json",
                "rest/v1/test_case_10/TravelCalculatePremiumResponse_selected_risks_is_wrong.json");
    }

    @Test
    @DisplayName("Test case 11: personBirthdayDate is null")
    public void personBirthdayDateIsNull() throws Exception{
        comparingJSON("rest/v1/test_case_11/TravelCalculatePremiumRequest_personBirthdayDate_does_not_exist.json",
                "rest/v1/test_case_11/TravelCalculatePremiumResponse_personBirthdayDate_does_not_exist.json");
    }

    @Test
    @DisplayName("Test case 12: personBirthdayDate in future")
    public void personBirthdayDateInFuture() throws Exception{
        comparingJSON("rest/v1/test_case_12/TravelCalculatePremiumRequest_personBirthdayDate_in_future.json",
                "rest/v1/test_case_12/TravelCalculatePremiumResponse_personBirthdayDate_in_future.json");
    }

    @Test
    @DisplayName("Test case 13: allFields not_provided")
    public void travelMedicalAllFieldsNotProvided() throws Exception{
        comparingJSON("rest/v1/test_case_13/TravelCalculatePremiumRequest_travel_medical_allFields_not_provided.json",
                "rest/v1/test_case_13/TravelCalculatePremiumResponse_travel_medical_allFields_not_provided.json");
    }

    @Test
    @DisplayName("Test case 14: all are okay")
    public void allFieldsAreCorrect()throws Exception{
        comparingJSON("rest/v1/test_case_14/TravelCalculatePremiumRequest_all_fields_are_okay.json",
                "rest/v1/test_case_14/TravelCalculatePremiumResponse_all_fields_are_okay.json");
    }

    @Test
    @DisplayName("Test case 15: country is empty")
    public void countryIsEmpty() throws Exception{
        comparingJSON("rest/v1/test_case_15/TravelCalculatePremiumRequest_country_empty.json",
                "rest/v1/test_case_15/TravelCalculatePremiumResponse_country_empty.json");
    }

    @Test
    @DisplayName("Test case 16: personCode null")
    public void personCodeNull()throws Exception{
        comparingJSON("rest/v1/test_case_16/TravelCalculatePremiumRequest_all_fields_are_okay.json",
                "rest/v1/test_case_16/TravelCalculatePremiumResponse_all_fields_are_okay.json");
    }

    @Test
    @DisplayName("Test case 17: personCode blunk")
    public void personCodeBlunk()throws Exception{
        comparingJSON("rest/v1/test_case_17/TravelCalculatePremiumRequest_all_fields_are_okay.json",
                "rest/v1/test_case_17/TravelCalculatePremiumResponse_all_fields_are_okay.json");
    }

    @Test
    @DisplayName("Test case 18: travelCost null")
    public void travelCostNull()throws Exception{
        comparingJSON("rest/v1/test_case_18/request.json",
                "rest/v1/test_case_18/response.json");
    }


    public void comparingJSON(String path1,String path2) throws Exception{
        MvcResult result =mockMvc.perform(post(BASE_URL)
                        .content(jsonFileReader.readJsonFromFile(path1))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String request = result.getResponse().getContentAsString();

        String response = jsonFileReader.readJsonFromFile(path2);

        //игнорирование UUID
        CustomComparator customComparator = new CustomComparator(
                JSONCompareMode.NON_EXTENSIBLE,
                new Customization("uuid", (o1, o2) -> true) // Игнорируем uuid
        );

        //порядок не важен
        JSONAssert.assertEquals(response, request, customComparator);
    }


}