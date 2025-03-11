package org.javaguru.travel.insurance.rest.v2.persons;

import org.javaguru.travel.insurance.rest.v2.CompareJSONV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonLevelV2TestCases extends CompareJSONV2 {

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
    @DisplayName("personCode is null")
    public void test_case_24() throws Exception{
        comparingJSON("rest/v2/test_case_24/request.json","rest/v2/test_case_24/response.json");
    }

    @Test
    @DisplayName("personCode is null twice")
    public void test_case_25() throws Exception{
        comparingJSON("rest/v2/test_case_25/request.json","rest/v2/test_case_25/response.json");
    }

    @Test
    @DisplayName("personCode is blunk")
    public void test_case_26() throws Exception{
        comparingJSON("rest/v2/test_case_26/request.json","rest/v2/test_case_26/response.json");
    }

    @Test
    @DisplayName("personCode is blunk : twice")
    public void test_case_27() throws Exception{
        comparingJSON("rest/v2/test_case_27/request.json","rest/v2/test_case_27/response.json");
    }

}
