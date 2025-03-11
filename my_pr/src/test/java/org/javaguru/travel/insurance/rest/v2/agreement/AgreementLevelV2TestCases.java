package org.javaguru.travel.insurance.rest.v2.agreement;

import org.javaguru.travel.insurance.rest.v2.CompareJSONV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgreementLevelV2TestCases extends CompareJSONV2 {

    @Test
    @DisplayName("all fields okay")
    public void test_case_1() throws Exception{
        comparingJSON("rest/v2/agreement/test_case_1/request.json", "rest/v2/agreement/test_case_1/response.json");
    }

    @Test
    @DisplayName("all fields null")
    public void test_case_2() throws Exception{
        comparingJSON("rest/v2/agreement/test_case_2/request.json", "rest/v2/agreement/test_case_2/response.json");
    }

    @Test
    @DisplayName("agreementDateFrom is null")
    public void test_case_3() throws Exception{
        comparingJSON("rest/v2/agreement/test_case_3/request.json", "rest/v2/agreement/test_case_3/response.json");
    }

    @Test
    @DisplayName("agreementDateTo is null")
    public void test_case_4() throws Exception{
        comparingJSON("rest/v2/agreement/test_case_4/request.json", "rest/v2/agreement/test_case_4/response.json");
    }

    @Test
    @DisplayName("agreementDateFrom after then agreementDateTo")
    public void test_case_5() throws Exception{
        comparingJSON("rest/v2/agreement/test_case_5/request.json", "rest/v2/agreement/test_case_5/response.json");
    }

    @Test
    @DisplayName("agreementDateFrom must be in future")
    public void test_case_6() throws Exception{
        comparingJSON("rest/v2/agreement/test_case_6/request.json", "rest/v2/agreement/test_case_6/response.json");
    }

    @Test
    @DisplayName("agreementDateTo must be in future")
    public void test_case_7() throws Exception{
        comparingJSON("rest/v2/agreement/test_case_7/request.json", "rest/v2/agreement/test_case_7/response.json");
    }

    @Test
    @DisplayName("country is null")
    public void test_case_8() throws Exception{
        comparingJSON("rest/v2/agreement/test_case_8/request.json", "rest/v2/agreement/test_case_8/response.json");
    }

    @Test
    @DisplayName("country is blank")
    public void test_case_9() throws Exception{
        comparingJSON("rest/v2/agreement/test_case_9/request.json", "rest/v2/agreement/test_case_9/response.json");
    }

    @Test
    @DisplayName("country is NOT_SUPPORTED ")
    public void test_case_21() throws Exception{
        comparingJSON("rest/v2/agreement/test_case_21/request.json", "rest/v2/agreement/test_case_21/response.json");
    }

}
