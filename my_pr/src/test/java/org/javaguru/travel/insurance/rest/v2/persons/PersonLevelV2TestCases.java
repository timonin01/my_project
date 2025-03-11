package org.javaguru.travel.insurance.rest.v2.persons;

import org.javaguru.travel.insurance.rest.v2.CompareJSONV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonLevelV2TestCases extends CompareJSONV2 {

    @Test
    @DisplayName("personFirstName is null")
    public void test_case_14() throws Exception{
        comparingJSON("rest/v2/persons/PersonFirstName_Is_Null/request.json", "rest/v2/persons/PersonFirstName_Is_Null/response.json");
    }

    @Test
    @DisplayName("personLastName is empty")
    public void test_case_15() throws Exception{
        comparingJSON("rest/v2/persons/PersonLastName_Is_Empty/request.json", "rest/v2/persons/PersonLastName_Is_Empty/response.json");
    }

    @Test
    @DisplayName("personBirthDate is empty")
    public void test_case_16() throws Exception{
        comparingJSON("rest/v2/persons/PersonBirthDate_Is_Empty/request.json", "rest/v2/persons/PersonBirthDate_Is_Empty/response.json");
    }

    @Test
    @DisplayName("personBirthDate must be in past")
    public void test_case_17() throws Exception{
        comparingJSON("rest/v2/persons/PersonBirthDate_Must_Be_In_Past/request.json", "rest/v2/persons/PersonBirthDate_Must_Be_In_Past/response.json");
    }

    @Test
    @DisplayName("personBirthDate must be in past with two people")
    public void test_case_18() throws Exception{
        comparingJSON("rest/v2/persons/PersonBirthDate_Must_Be_In_Past_With_Two_People/request.json", "rest/v2/persons/PersonBirthDate_Must_Be_In_Past_With_Two_People/response.json");
    }

    @Test
    @DisplayName("personCode is null")
    public void test_case_24() throws Exception{
        comparingJSON("rest/v2/persons/PersonCode_Is_Null/request.json", "rest/v2/persons/PersonCode_Is_Null/response.json");
    }

    @Test
    @DisplayName("personCode is null twice")
    public void test_case_25() throws Exception{
        comparingJSON("rest/v2/persons/PersonCode_Is_Null_Twice/request.json", "rest/v2/persons/PersonCode_Is_Null_Twice/response.json");
    }

    @Test
    @DisplayName("personCode is blunk")
    public void test_case_26() throws Exception{
        comparingJSON("rest/v2/persons/PersonCode_Is_Blunk/request.json", "rest/v2/persons/PersonCode_Is_Blunk/response.json");
    }

    @Test
    @DisplayName("personCode is blunk : twice")
    public void test_case_27() throws Exception{
        comparingJSON("rest/v2/persons/PersonCode_Is_Blunk_Twice/request.json", "rest/v2/persons/PersonCode_Is_Blunk_Twice/response.json");
    }

}
