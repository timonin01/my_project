package org.javaguru.travel.insurance.rest.v1.risks;

import org.javaguru.travel.insurance.rest.v1.CompareJSONV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class RiskLevelV1TestCases extends CompareJSONV1 {

    @Test
    @DisplayName("ERROR_CODE_6: selected_risks is null")
    public void selectedRisksIsNull()throws Exception{
        comparingJSON("rest/v1/test_case_8/TravelCalculatePremiumRequest_selected_risks_is_null.json",
                "rest/v1/test_case_8/TravelCalculatePremiumResponse_selected_risks_is_null.json");
    }

    @Test
    @DisplayName("ERROR_CODE_6: selected_risks is empty")
    public void selectedRisksIsEmpty()throws Exception{
        comparingJSON("rest/v1/test_case_9/TravelCalculatePremiumRequest_selected_risks_is_empty.json",
                "rest/v1/test_case_9/TravelCalculatePremiumResponse_selected_risks_is_empty.json");
    }

    @Test
    @DisplayName("ERROR_CODE_9: selected_risks are wrong")
    public void selectedRisksAreWrong() throws Exception{
        comparingJSON("rest/v1/test_case_10/TravelCalculatePremiumRequest_selected_risks_is_wrong.json",
                "rest/v1/test_case_10/TravelCalculatePremiumResponse_selected_risks_is_wrong.json");
    }

}
