package org.javaguru.travel.insurance.rest.v2.sport_activity;

import org.javaguru.travel.insurance.rest.v2.CompareJSONV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SportActivityRiskV2TestCases extends CompareJSONV2 {

    @Test
    @DisplayName("sportActivity is null")
    public void sportActivity_Is_Null() throws Exception{
        comparingJSON("rest/v2/sport_activity/ERROR_CODE_25_SportActivityIsNull/request.json",
            "rest/v2/sport_activity/ERROR_CODE_25_SportActivityIsNull/response.json");
    }

    @Test
    @DisplayName("sportActivity is null Twice")
    public void sportActivity_Is_Null_Twice() throws Exception{
        comparingJSON("rest/v2/sport_activity/ERROR_CODE_25_SportActivityIsNullTwice/request.json",
            "rest/v2/sport_activity/ERROR_CODE_25_SportActivityIsNullTwice/response.json");
    }

    @Test
    @DisplayName("sportActivity is blank")
    public void sportActivity_Is_Blank() throws Exception{
        comparingJSON("rest/v2/sport_activity/ERROR_CODE_25_SportActivityIsBlank/request.json",
            "rest/v2/sport_activity/ERROR_CODE_25_SportActivityIsBlank/response.json");
    }

    @Test
    @DisplayName("sportActivity is blank twice")
    public void sportActivity_Is_Blank_Twice() throws Exception{
        comparingJSON("rest/v2/sport_activity/ERROR_CODE_25_SportActivityIsBlankTwice/request.json",
            "rest/v2/sport_activity/ERROR_CODE_25_SportActivityIsBlankTwice/response.json");
    }

    @Test
    @DisplayName("sportActivity incorrect")
    public void sportActivity_Incorrect() throws Exception{
        comparingJSON("rest/v2/sport_activity/ERROR_CODE_25_SportActivityIsBlankTwice/request.json",
            "rest/v2/sport_activity/ERROR_CODE_25_SportActivityIsBlankTwice/response.json");
    }

    @Test
    @DisplayName("sportActivity incorrect twice")
    public void sportActivity_Incorrect_Twice() throws Exception{
        comparingJSON("rest/v2/sport_activity/ERROR_CODE_25_SportActivityIsBlankTwice/request.json",
            "rest/v2/sport_activity/ERROR_CODE_25_SportActivityIsBlankTwice/response.json");
    }

}
