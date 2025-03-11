package org.javaguru.travel.insurance.rest.v2.risk_travel_medical;

import org.javaguru.travel.insurance.rest.v2.CompareJSONV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelMedicalRiskV2TestCases extends CompareJSONV2 {

    @Test
    @DisplayName("medicalRiskLimitLevel is null")
    public void test_case_10() throws Exception{
        comparingJSON("rest/v2/test_case_10/request.json","rest/v2/test_case_10/response.json");
    }

    @Test
    @DisplayName("medicalRiskLimitLevel is blank")
    public void test_case_11() throws Exception{
        comparingJSON("rest/v2/test_case_11/request.json","rest/v2/test_case_11/response.json");
    }

    @Test
    @DisplayName("medicalRiskLimitLevel is NOT_SUPPORTED")
    public void test_case_22() throws Exception{
        comparingJSON("rest/v2/test_case_22/request.json","rest/v2/test_case_22/response.json");
    }

    @Test
    @DisplayName("medicalRiskLimitLevel is NOT_SUPPORTED twice")
    public void test_case_23() throws Exception{
        comparingJSON("rest/v2/test_case_23/request.json","rest/v2/test_case_23/response.json");
    }

}
