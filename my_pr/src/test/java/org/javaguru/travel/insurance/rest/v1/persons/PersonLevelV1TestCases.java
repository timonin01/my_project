package org.javaguru.travel.insurance.rest.v1.persons;

import org.javaguru.travel.insurance.rest.v1.CompareJSONV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class PersonLevelV1TestCases extends CompareJSONV1 {

    @Test
    @DisplayName("ERROR_CODE_7: firstName does not exist")
    public void firstNameDoesNotExist()throws Exception{
        comparingJSON("rest/v1/persons/ERROR_CODE_7_FirstName_Does_Not_Exist/request.json",
                "rest/v1/persons/ERROR_CODE_7_FirstName_Does_Not_Exist/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_22: firstName incorrect")
    public void firstNameincorrect()throws Exception{
        comparingJSON("rest/v1/persons/ERROR_CODE_22_FirstName_Incorrect/request.json",
                "rest/v1/persons/ERROR_CODE_22_FirstName_Incorrect/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_8: lastName does not exist")
    public void lastNameDoesNotExist()throws Exception{
        comparingJSON("rest/v1/persons/ERROR_CODE_8_LastName_Does_Not_Exist/request.json",
                "rest/v1/persons/ERROR_CODE_8_LastName_Does_Not_Exist/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_8: lastName incorrect")
    public void lastNameDoesIncorrect()throws Exception{
        comparingJSON("rest/v1/persons/ERROR_CODE_23_LastName_Incorrect/request.json",
                "rest/v1/persons/ERROR_CODE_23_LastName_Incorrect/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_11: personBirthdayDate is null")
    public void personBirthdayDateIsNull() throws Exception{
        comparingJSON("rest/v1/persons/ERROR_CODE_11_PersonBirthdayDate_Is_Null/request.json",
                "rest/v1/persons/ERROR_CODE_11_PersonBirthdayDate_Is_Null/resonse.json");
    }

    @Test
    @DisplayName("ERROR_CODE_12: personBirthdayDate in future")
    public void personBirthdayDateInFuture() throws Exception{
        comparingJSON("rest/v1/persons/ERROR_CODE_12_PersonBirthdayDate_In_Future/request.json",
                "rest/v1/persons/ERROR_CODE_12_PersonBirthdayDate_In_Future/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_16: personCode null")
    public void personCodeNull()throws Exception{
        comparingJSON("rest/v1/persons/ERROR_CODE_16_PersonCode_Null/request.json",
                "rest/v1/persons/ERROR_CODE_16_PersonCode_Null/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_16: personCode blunk")
    public void personCodeBlunk()throws Exception{
        comparingJSON("rest/v1/persons/ERROR_CODE_16_PersonCode_Blank/request.json",
                "rest/v1/persons/ERROR_CODE_16_PersonCode_Blank/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_21: personCode incorrect")
    public void personCodeincorrect()throws Exception{
        comparingJSON("rest/v1/persons/ERROR_CODE_21_PersonCode_Incorrect/request.json",
                "rest/v1/persons/ERROR_CODE_21_PersonCode_Incorrect/response.json");
    }



}
