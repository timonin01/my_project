package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test case 1: firstName does not exist")
    public void firstNameDoesNotExist()throws Exception{
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : null,\n" +
                                "\"personLastName\" : \"Pupkin\",\n" +
                                "\"agreementDateFrom\" : \"2021-05-25\",\n" +
                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personFirstName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 2: lastName does not exist")
    public void lastNameDoesNotExist()throws Exception{
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Vasja\",\n" +
                                "\"personLastName\" : null,\n" +
                                "\"agreementDateFrom\" : \"2021-05-25\",\n" +
                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("personLastName")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 3: agreementDateFrom does not exist")
    public void agreementDateFromDoesNotExist()throws Exception{
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Vasja\",\n" +
                                "\"personLastName\" : \"Pupkin\",\n" +
                                "\"agreementDateFrom\" : null,\n" +
                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateFrom")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 4: agreementDateTo does not exist")
    public void agreementDateToDoesNotExist()throws Exception{
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Vasja\",\n" +
                                "\"personLastName\" : \"Pupkin\",\n" +
                                "\"agreementDateFrom\" : \"2021-05-25\",\n" +
                                "\"agreementDateTo\" : null\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[0].message", is("Must not be empty!")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 5: all fields does not exist")
    public void allFieldsDoesNotExist()throws Exception{
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : null,\n" +
                                "\"personLastName\" : null,\n" +
                                "\"agreementDateFrom\" : null,\n" +
                                "\"agreementDateTo\" : null\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(4)))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 6: agreementDateTo does not exist")
    public void agreementDateToMustBeAfterThenAgreementDateFrom()throws Exception{
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Vasja\",\n" +
                                "\"personLastName\" : \"Pupkin\",\n" +
                                "\"agreementDateFrom\" : \"2021-05-29\",\n" +
                                "\"agreementDateTo\" : \"2021-05-19\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andExpect(jsonPath("errors", is(notNullValue())))
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0].field", is("agreementDateTo")))
                .andExpect(jsonPath("errors[0].message", is("Must be after then agreementDateFrom")))
                .andReturn();
    }

    @Test
    @DisplayName("Test case 7: all are okay")
    public void allFieldsAreCorrect()throws Exception{
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Vasja\",\n" +
                                "\"personLastName\" : \"Pupkin\",\n" +
                                "\"agreementDateFrom\" : \"2021-05-29\",\n" +
                                "\"agreementDateTo\" : \"2021-05-30\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Vasja")))
                .andExpect(jsonPath("personLastName", is("Pupkin")))
                .andExpect(jsonPath("agreementDateFrom", is("2021-05-29")))
                .andExpect(jsonPath("agreementDateTo", is("2021-05-30")))
                .andExpect(jsonPath("agreementPrice", is(1)))
                .andExpect(jsonPath("errors", is(nullValue())))
                .andReturn();
    }

}