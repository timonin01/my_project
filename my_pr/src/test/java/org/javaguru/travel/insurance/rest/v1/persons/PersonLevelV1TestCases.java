package org.javaguru.travel.insurance.rest.v1.persons;

import org.javaguru.travel.insurance.rest.v1.CompareJSONV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class PersonLevelV1TestCases extends CompareJSONV1 {

    @Test
    @DisplayName("ERROR_CODE_7: firstName does not exist")
    public void firstNameDoesNotExist()throws Exception{
        comparingJSON("rest/v1/persons/test_case_1/request.json",
                "rest/v1/persons/test_case_1/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_8: lastName does not exist")
    public void lastNameDoesNotExist()throws Exception{
        comparingJSON("rest/v1/persons/test_case_2/request.json",
                "rest/v1/persons/test_case_2/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_11: personBirthdayDate is null")
    public void personBirthdayDateIsNull() throws Exception{
        comparingJSON("rest/v1/persons/test_case_11/request.json",
                "rest/v1/persons/test_case_11/resonse.json");
    }

    @Test
    @DisplayName("ERROR_CODE_12: personBirthdayDate in future")
    public void personBirthdayDateInFuture() throws Exception{
        comparingJSON("rest/v1/persons/test_case_12/request.json",
                "rest/v1/persons/test_case_12/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_16: personCode null")
    public void personCodeNull()throws Exception{
        comparingJSON("rest/v1/persons/test_case_16/request.json",
                "rest/v1/persons/test_case_16/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_16: personCode blunk")
    public void personCodeBlunk()throws Exception{
        comparingJSON("rest/v1/persons/test_case_17/request.json",
                "rest/v1/persons/test_case_17/response.json");
    }

}
