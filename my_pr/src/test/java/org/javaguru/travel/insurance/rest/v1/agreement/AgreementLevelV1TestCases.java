package org.javaguru.travel.insurance.rest.v1.agreement;

import org.javaguru.travel.insurance.rest.v1.CompareJSONV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgreementLevelV1TestCases extends CompareJSONV1 {

    @Test
    @DisplayName("all fields does not exist")
    public void allFieldsDoesNotExist()throws Exception{
        comparingJSON("rest/v1/agreement/All_Fields_Does_Not_Exist/request.json",
                "rest/v1/agreement/All_Fields_Does_Not_Exist/response.json");
    }

    @Test
    @DisplayName("all Fields are okay")
    public void allFieldsAreCorrect()throws Exception{
        comparingJSON("rest/v1/agreement/All_Fields_Are_Okay/request.json",
                "rest/v1/agreement/All_Fields_Are_Okay/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_2: agreementDateFrom does not exist")
    public void agreementDateFromDoesNotExist()throws Exception{
        comparingJSON("rest/v1/agreement/ERROR_CODE_2_AgreementDateFrom_Does_Not_Exist/request.json",
                "rest/v1/agreement/ERROR_CODE_2_AgreementDateFrom_Does_Not_Exist/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_4: agreementDateTo does not exist")
    public void agreementDateToDoesNotExist()throws Exception{
        comparingJSON("rest/v1/agreement/ERROR_CODE_4_AgreementDateTo_Does_Not_Exist/request.json",
                "rest/v1/agreement/ERROR_CODE_4_AgreementDateTo_Does_Not_Exist/response.json");
    }
    @Test
    @DisplayName("ERROR_CODE_5: agreementDateTo must be after then agreement agreementDateFrom")
    public void agreementDateToMustBeAfterThenAgreementDateFrom()throws Exception{
        comparingJSON("rest/v1/agreement/ERROR_CODE_5_AgreementDateTo_Must_Be_After_Then_Agreement_AgreementDateFrom/request.json",
                "rest/v1/agreement/ERROR_CODE_5_AgreementDateTo_Must_Be_After_Then_Agreement_AgreementDateFrom/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_10: country is null")
    public void countryIsNull() throws Exception{
        comparingJSON("rest/v1/agreement/ERROR_CODE_10_Country_Is_Null/request.json",
                "rest/v1/agreement/ERROR_CODE_10_Country_Is_Null/response.json");
    }

    @Test
    @DisplayName("ERROR_CODE_10: country is empty")
    public void countryIsEmpty() throws Exception{
        comparingJSON("rest/v1/agreement/ERROR_CODE_10_Country_Is_Empty/request.json",
                "rest/v1/agreement/ERROR_CODE_10_Country_Is_Empty/response.json");
    }

}
