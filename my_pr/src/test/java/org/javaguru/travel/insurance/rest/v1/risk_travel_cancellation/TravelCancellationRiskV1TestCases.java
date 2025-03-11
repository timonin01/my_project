package org.javaguru.travel.insurance.rest.v1.risk_travel_cancellation;

import org.javaguru.travel.insurance.rest.v1.CompareJSONV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCancellationRiskV1TestCases extends CompareJSONV1 {

    @Test
    @DisplayName("ERROR_CODE_19: travelCost null")
    public void travelCostNull()throws Exception{
        comparingJSON("rest/v1/risk_travel_cancellation/test_case_18/request.json",
                "rest/v1/risk_travel_cancellation/test_case_18/response.json");
    }

}
