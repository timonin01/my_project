package org.javaguru.travel.insurance.rest.v1.risk_travel_medical;

import org.javaguru.travel.insurance.rest.v1.CompareJSONV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelMedicalRiskV1TestCases extends CompareJSONV1 {

    @Test
    @DisplayName("all Fields not_provided except selected_risks")
    public void travelMedicalAllFieldsNotProvided() throws Exception{
        comparingJSON("rest/v1/risk_travel_medical/test_case_13/request.json",
                "rest/v1/risk_travel_medical/test_case_13/response.json");
    }

}
