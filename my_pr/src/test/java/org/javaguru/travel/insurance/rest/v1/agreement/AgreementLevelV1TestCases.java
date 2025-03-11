package org.javaguru.travel.insurance.rest.v1.agreement;

import org.javaguru.travel.insurance.rest.v1.CompareJSONV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class AgreementLevelV1TestCases extends CompareJSONV1 {

    @Test
    @DisplayName("all fields does not exist")
    public void allFieldsDoesNotExist()throws Exception{
        comparingJSON("rest/v1/agreement/test_case_5/request.json",
                "rest/v1/agreement/test_case_5/response.json");
    }


    @Test
    @DisplayName("all Fields are okay")
    public void allFieldsAreCorrect()throws Exception{
        comparingJSON("rest/v1/agreement/test_case_14/request.json",
                "rest/v1/agreement/test_case_14/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_2: agreementDateFrom does not exist")
    public void agreementDateFromDoesNotExist()throws Exception{
        comparingJSON("rest/v1/agreement/test_case_3/request.json",
                "rest/v1/agreement/test_case_3/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_4: agreementDateTo does not exist")
    public void agreementDateToDoesNotExist()throws Exception{
        comparingJSON("rest/v1/agreement/test_case_4/request.json",
                "rest/v1/agreement/test_case_4/response.json");
    }
    @Test
    @DisplayName("ERROR_CODE_5: agreementDateTo must be after then agreement agreementDateFrom")
    public void agreementDateToMustBeAfterThenAgreementDateFrom()throws Exception{
        comparingJSON("rest/v1/agreement/test_case_6/request.json",
                "rest/v1/agreement/test_case_6/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_10: country is null")
    public void countryIsNull() throws Exception{
        comparingJSON("rest/v1/agreement/test_case_7/request.json",
                "rest/v1/agreement/test_case_7/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_10: country is empty")
    public void countryIsEmpty() throws Exception{
        comparingJSON("rest/v1/agreement/test_case_15/request.json",
                "rest/v1/agreement/test_case_15/response.json");
    }

}
