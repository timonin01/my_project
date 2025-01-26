package org.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFileReader;

    @Test
    public void shouldReturnValidResponseWithOutErrors() throws Exception {
        executeAndCompare("rest/requests/valid_request.json", "rest/responses/valid_response.json");
    }

    @Test
    public void shouldReturnResponseWithDateFromError() throws Exception {
        executeAndCompare("rest/requests/date_from_is_nul_request.json", "rest/responses/date_from_is_null_response.json");
    }

    @Test
    public void shouldReturnResponseWithDateToError() throws Exception {
        executeAndCompare("rest/requests/date_to_is_null_request.json", "rest/responses/date_to_is_null_response.json");
    }

    @Test
    public void shouldReturnResponseWithFirstNameError() throws Exception {
        executeAndCompare("rest/requests/first_name_is_null_request.json", "rest/responses/first_name_is_null_response.json");
    }

    @Test
    public void shouldReturnResponseWithLastNameError() throws Exception {
        executeAndCompare("rest/requests/last_name_is_null_request.json", "rest/responses/last_name_is_null_response.json");
    }

    @Test
    public void shouldReturnResponseWithAllErrors() throws Exception {
        executeAndCompare("rest/requests/all_errors_request.json", "rest/responses/all_errors_response.json");
    }



    private void executeAndCompare(String request, String response) throws Exception {
        var objectMapper = new ObjectMapper();

        var actualJson = mockMvc.perform(post("/insurance/travel/").content(jsonFileReader.readJsonFromFile(
                    request))
                 .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        Assertions.assertThat(objectMapper.readTree(actualJson)).isEqualTo(objectMapper.readTree(
               jsonFileReader.readJsonFromFile(response)));
    }

}
