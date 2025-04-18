package org.javaguru.blacklist.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackListTestCases extends CompareJSON{

    @Test
    @DisplayName("people not in blacklist")
    public void peopleNotInBlackList()throws Exception{
        executeAndCompare("/PeopleNotInBlackList");
    }

    @Test
    @DisplayName("people in blacklist")
    public void peopleInBlackList()throws Exception{
        executeAndCompare("/PeopleInBlackList");
    }

    @Test
    @DisplayName("firstName empty")
    public void firstNameEmpty()throws Exception{
        executeAndCompare("ERROR_CODE_1_FirstName_empty");
    }

    @Test
    @DisplayName("firstName is null")
    public void firstNameIsNull()throws Exception{
        executeAndCompare("ERROR_CODE_1_FirstName_is_null");
    }

    @Test
    @DisplayName("lastName empty")
    public void lastNameEmpty()throws Exception{
        executeAndCompare("ERROR_CODE_2_LastName_empty");
    }

    @Test
    @DisplayName("lastName is null")
    public void lastNameIsNull()throws Exception{
        executeAndCompare("ERROR_CODE_2_LastName_is_null");
    }

    @Test
    @DisplayName("personCode empty")
    public void personCodeEmpty()throws Exception{
        executeAndCompare("ERROR_CODE_3_PersonCode_empty");
    }

    @Test
    @DisplayName("personCode is null")
    public void personCodeIsNull()throws Exception{
        executeAndCompare("ERROR_CODE_3_PersonCode_is_null");
    }

}
