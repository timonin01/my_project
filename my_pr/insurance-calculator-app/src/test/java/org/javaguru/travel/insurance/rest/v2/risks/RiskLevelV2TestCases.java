package org.javaguru.travel.insurance.rest.v2.risks;

import org.javaguru.travel.insurance.rest.v2.CompareJSONV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RiskLevelV2TestCases extends CompareJSONV2 {

    @Test
    @DisplayName("selected_risks is null")
    public void test_case_12() throws Exception{
        comparingJSON("rest/v2/risks/Selected_risks_Is_Null/request.json", "rest/v2/risks/Selected_risks_Is_Null/response.json");
    }

    @Test
    @DisplayName("selected_risks is empty")
    public void test_case_13() throws Exception{
        comparingJSON("rest/v2/risks/Selected_risks_Is_Empty/request.json", "rest/v2/risks/Selected_risks_Is_Empty/response.json");
    }

    @Test
    @DisplayName("selected_risks is NOT_SUPPORTED")
    public void test_case_19() throws Exception{
        comparingJSON("rest/v2/risks/Selected_risks_Is_NOT_SUPPORTED/request.json", "rest/v2/risks/Selected_risks_Is_NOT_SUPPORTED/response.json");
    }

    @Test
    @DisplayName("selected_risks is NOT_SUPPORTED twice")
    public void test_case_20() throws Exception{
        comparingJSON("rest/v2/risks/Selected_risks_Is_NOT_SUPPORTED_Twice/request.json", "rest/v2/risks/Selected_risks_Is_NOT_SUPPORTED_Twice/response.json");
    }

}
