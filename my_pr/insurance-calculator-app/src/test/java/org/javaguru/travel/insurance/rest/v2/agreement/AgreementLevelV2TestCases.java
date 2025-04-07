package org.javaguru.travel.insurance.rest.v2.agreement;

import org.javaguru.travel.insurance.rest.v2.CompareJSONV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgreementLevelV2TestCases extends CompareJSONV2 {

    @Test
    @DisplayName("all fields okay")
    public void test_case_1() throws Exception{
        comparingJSON("rest/v2/agreement/All_Fields_Okay/request.json", "rest/v2/agreement/All_Fields_Okay/response.json");
    }

    @Test
    @DisplayName("all fields null")
    public void test_case_2() throws Exception{
        comparingJSON("rest/v2/agreement/All_Fields_Null/request.json", "rest/v2/agreement/All_Fields_Null/response.json");
    }

    @Test
    @DisplayName("agreementDateFrom is null")
    public void test_case_3() throws Exception{
        comparingJSON("rest/v2/agreement/AgreementDateFrom_Is_Null/request.json", "rest/v2/agreement/AgreementDateFrom_Is_Null/response.json");
    }

    @Test
    @DisplayName("agreementDateTo is null")
    public void test_case_4() throws Exception{
        comparingJSON("rest/v2/agreement/AgreementDateTo_Is_Null/request.json", "rest/v2/agreement/AgreementDateTo_Is_Null/response.json");
    }

    @Test
    @DisplayName("agreementDateFrom after then agreementDateTo")
    public void test_case_5() throws Exception{
        comparingJSON("rest/v2/agreement/AgreementDateFrom_After_Then_AgreementDateTo/request.json", "rest/v2/agreement/AgreementDateFrom_After_Then_AgreementDateTo/response.json");
    }

    @Test
    @DisplayName("agreementDateFrom must be in future")
    public void test_case_6() throws Exception{
        comparingJSON("rest/v2/agreement/AgreementDateFrom_Must_Be_In_Future/request.json", "rest/v2/agreement/AgreementDateFrom_Must_Be_In_Future/response.json");
    }

    @Test
    @DisplayName("agreementDateTo must be in future")
    public void test_case_7() throws Exception{
        comparingJSON("rest/v2/agreement/AgreementDateTo_Must_Be_In_Future/request.json", "rest/v2/agreement/AgreementDateTo_Must_Be_In_Future/response.json");
    }

    @Test
    @DisplayName("country is null")
    public void test_case_8() throws Exception{
        comparingJSON("rest/v2/agreement/Country_Is_Null/request.json", "rest/v2/agreement/Country_Is_Null/response.json");
    }

    @Test
    @DisplayName("country is blank")
    public void test_case_9() throws Exception{
        comparingJSON("rest/v2/agreement/Country_Is_Blank/request.json", "rest/v2/agreement/Country_Is_Blank/response.json");
    }

    @Test
    @DisplayName("country is NOT_SUPPORTED ")
    public void test_case_21() throws Exception{
        comparingJSON("rest/v2/agreement/Country_Is_NOT_SUPPORTED/request.json", "rest/v2/agreement/Country_Is_NOT_SUPPORTED/response.json");
    }

}
