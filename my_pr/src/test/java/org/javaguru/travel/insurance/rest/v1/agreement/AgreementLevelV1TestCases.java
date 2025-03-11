package org.javaguru.travel.insurance.rest.v1.agreement;

import org.javaguru.travel.insurance.rest.v1.CompareJSONV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class AgreementLevelV1TestCases extends CompareJSONV1 {

    @Test
    @DisplayName("all fields does not exist")
    public void allFieldsDoesNotExist()throws Exception{
        comparingJSON("rest/v1/test_case_5/TravelCalculatePremiumRequest_all_fields_does_not_exist.json",
                "rest/v1/test_case_5/TravelCalculatePremiumResponse_all_fields_does_not_exist.json");
    }


    @Test
    @DisplayName("all Fields are okay")
    public void allFieldsAreCorrect()throws Exception{
        comparingJSON("rest/v1/test_case_14/TravelCalculatePremiumRequest_all_fields_are_okay.json",
                "rest/v1/test_case_14/TravelCalculatePremiumResponse_all_fields_are_okay.json");
    }

    @Test
    @DisplayName("ERROR_CODE_2: agreementDateFrom does not exist")
    public void agreementDateFromDoesNotExist()throws Exception{
        comparingJSON("rest/v1/test_case_3/TravelCalculatePremiumRequest_agreementDateFrom_does_not_exist.json",
                "rest/v1/test_case_3/TravelCalculatePremiumResponse_agreementDateFrom_does_not_exist.json");
    }

    @Test
    @DisplayName("ERROR_CODE_4: agreementDateTo does not exist")
    public void agreementDateToDoesNotExist()throws Exception{
        comparingJSON("rest/v1/test_case_4/TravelCalculatePremiumRequest_agreementDateTo_does_not_exist.json",
                "rest/v1/test_case_4/TravelCalculatePremiumResponse_agreementDateTo_does_not_exist.json");
    }
    @Test
    @DisplayName("ERROR_CODE_5: agreementDateTo must be after then agreement agreementDateFrom")
    public void agreementDateToMustBeAfterThenAgreementDateFrom()throws Exception{
        comparingJSON("rest/v1/test_case_6/TravelCalculatePremiumRequest_agreementDateFrom_must_be_after_then_agreementDateTo.json",
                "rest/v1/test_case_6/TravelCalculatePremiumResponse_agreementDateFrom_must_be_after_then_agreementDateTo.json");
    }

    @Test
    @DisplayName("ERROR_CODE_10: country is null")
    public void countryIsNull() throws Exception{
        comparingJSON("rest/v1/test_case_7/TravelCalculatePremiumRequest_country_does_not_exist.json",
                "rest/v1/test_case_7/TravelCalculatePremiumResponse_country_does_not_exist.json");
    }

    @Test
    @DisplayName("ERROR_CODE_10: country is empty")
    public void countryIsEmpty() throws Exception{
        comparingJSON("rest/v1/test_case_15/TravelCalculatePremiumRequest_country_empty.json",
                "rest/v1/test_case_15/TravelCalculatePremiumResponse_country_empty.json");
    }

}
