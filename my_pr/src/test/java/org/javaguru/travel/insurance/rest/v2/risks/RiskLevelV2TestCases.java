package org.javaguru.travel.insurance.rest.v2.risks;

import org.javaguru.travel.insurance.rest.v2.CompareJSONV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RiskLevelV2TestCases extends CompareJSONV2 {

    @Test
    @DisplayName("selected_risks is null")
    public void test_case_12() throws Exception{
        comparingJSON("rest/v2/test_case_12/request.json","rest/v2/test_case_12/response.json");
    }

    @Test
    @DisplayName("selected_risks is empty")
    public void test_case_13() throws Exception{
        comparingJSON("rest/v2/test_case_13/request.json","rest/v2/test_case_13/response.json");
    }

    @Test
    @DisplayName("selected_risks is NOT_SUPPORTED")
    public void test_case_19() throws Exception{
        comparingJSON("rest/v2/test_case_19/request.json","rest/v2/test_case_19/response.json");
    }

    @Test
    @DisplayName("selected_risks is NOT_SUPPORTED twice")
    public void test_case_20() throws Exception{
        comparingJSON("rest/v2/test_case_20/request.json","rest/v2/test_case_20/response.json");
    }

}
