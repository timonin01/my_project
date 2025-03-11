package org.javaguru.travel.insurance.rest.v1.persons;

import org.javaguru.travel.insurance.rest.v1.CompareJSONV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class PersonLevelV1TestCases extends CompareJSONV1 {

    @Test
    @DisplayName("ERROR_CODE_7: firstName does not exist")
    public void firstNameDoesNotExist()throws Exception{
        comparingJSON("rest/v1/test_case_1/TravelCalculatePremiumRequest_firstname_does_not_exist.json",
                "rest/v1/test_case_1/TravelCalculatePremiumResponse_firstname_does_not_exist.json");
    }

    @Test
    @DisplayName("ERROR_CODE_8: lastName does not exist")
    public void lastNameDoesNotExist()throws Exception{
        comparingJSON("rest/v1/test_case_2/TravelCalculatePremiumRequest_lastname_does_not_exist.json",
                "rest/v1/test_case_2/TravelCalculatePremiumResponse_lastname_does_not_exist.json");
    }

    @Test
    @DisplayName("ERROR_CODE_11: personBirthdayDate is null")
    public void personBirthdayDateIsNull() throws Exception{
        comparingJSON("rest/v1/test_case_11/TravelCalculatePremiumRequest_personBirthdayDate_does_not_exist.json",
                "rest/v1/test_case_11/TravelCalculatePremiumResponse_personBirthdayDate_does_not_exist.json");
    }

    @Test
    @DisplayName("ERROR_CODE_12: personBirthdayDate in future")
    public void personBirthdayDateInFuture() throws Exception{
        comparingJSON("rest/v1/test_case_12/TravelCalculatePremiumRequest_personBirthdayDate_in_future.json",
                "rest/v1/test_case_12/TravelCalculatePremiumResponse_personBirthdayDate_in_future.json");
    }

    @Test
    @DisplayName("ERROR_CODE_16: personCode null")
    public void personCodeNull()throws Exception{
        comparingJSON("rest/v1/test_case_16/TravelCalculatePremiumRequest_all_fields_are_okay.json",
                "rest/v1/test_case_16/TravelCalculatePremiumResponse_all_fields_are_okay.json");
    }

    @Test
    @DisplayName("ERROR_CODE_16: personCode blunk")
    public void personCodeBlunk()throws Exception{
        comparingJSON("rest/v1/test_case_17/TravelCalculatePremiumRequest_all_fields_are_okay.json",
                "rest/v1/test_case_17/TravelCalculatePremiumResponse_all_fields_are_okay.json");
    }

}
