package org.javaguru.travel.insurance.rest.v2.risk_travel_medical;

import org.javaguru.travel.insurance.rest.v2.CompareJSONV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelMedicalRiskV2TestCases extends CompareJSONV2 {

    @Test
    @DisplayName("medicalRiskLimitLevel is null")
    public void test_case_10() throws Exception{
        comparingJSON("rest/v2/risk_travel_medical/MedicalRiskLimitLevel_Is_Null/request.json", "rest/v2/risk_travel_medical/MedicalRiskLimitLevel_Is_Null/response.json");
    }

    @Test
    @DisplayName("medicalRiskLimitLevel is blank")
    public void test_case_11() throws Exception{
        comparingJSON("rest/v2/risk_travel_medical/MedicalRiskLimitLevel_Is_Blank/request.json", "rest/v2/risk_travel_medical/MedicalRiskLimitLevel_Is_Blank/response.json");
    }

    @Test
    @DisplayName("medicalRiskLimitLevel is NOT_SUPPORTED")
    public void test_case_22() throws Exception{
        comparingJSON("rest/v2/risk_travel_medical/MedicalRiskLimitLevel_Is_NOT_SUPPORTED/request.json", "rest/v2/risk_travel_medical/MedicalRiskLimitLevel_Is_NOT_SUPPORTED/response.json");
    }

    @Test
    @DisplayName("medicalRiskLimitLevel is NOT_SUPPORTED twice")
    public void test_case_23() throws Exception{
        comparingJSON("rest/v2/risk_travel_medical/MedicalRiskLimitLevel_Is_NOT_SUPPORTED_Twice/request.json", "rest/v2/risk_travel_medical/MedicalRiskLimitLevel_Is_NOT_SUPPORTED_Twice/response.json");
    }

}
