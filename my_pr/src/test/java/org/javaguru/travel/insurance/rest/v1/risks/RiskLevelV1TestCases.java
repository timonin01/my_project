package org.javaguru.travel.insurance.rest.v1.risks;

import org.javaguru.travel.insurance.rest.v1.CompareJSONV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class RiskLevelV1TestCases extends CompareJSONV1 {

    @Test
    @DisplayName("ERROR_CODE_6: selected_risks is null")
    public void selectedRisksIsNull()throws Exception{
        comparingJSON("rest/v1/risks/ERROR_CODE_6_Selected_risks_Is_Null/request.json",
                "rest/v1/risks/ERROR_CODE_6_Selected_risks_Is_Null/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_6: selected_risks is empty")
    public void selectedRisksIsEmpty()throws Exception{
        comparingJSON("rest/v1/risks/ERROR_CODE_6_Selected_risks_Is_Empty/request.json",
                "rest/v1/risks/ERROR_CODE_6_Selected_risks_Is_Empty/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_9: selected_risks are wrong")
    public void selectedRisksAreWrong() throws Exception{
        comparingJSON("rest/v1/risks/ERROR_CODE_9_Selected_risks_Are_Wrong/request.json",
                "rest/v1/risks/ERROR_CODE_9_Selected_risks_Are_Wrong/response.json");
    }

}
