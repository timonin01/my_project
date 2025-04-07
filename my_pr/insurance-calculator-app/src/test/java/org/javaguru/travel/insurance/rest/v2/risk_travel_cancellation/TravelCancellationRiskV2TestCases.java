package org.javaguru.travel.insurance.rest.v2.risk_travel_cancellation;

import org.javaguru.travel.insurance.rest.v2.CompareJSONV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCancellationRiskV2TestCases extends CompareJSONV2 {

    @Test
    @DisplayName("travelCost is null")
    public void test_case_28() throws Exception{
        comparingJSON("rest/v2/risk_travel_cancellation/TravelCost_Is_Null/request.json", "rest/v2/risk_travel_cancellation/TravelCost_Is_Null/response.json");
    }

}
