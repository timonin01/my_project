package org.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.MvcResult;

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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private JsonFileReader jsonFileReader;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Test case 1: firstName does not exist")
    public void firstNameDoesNotExist()throws Exception{
        MvcResult result =  mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("TravelCalculatePremiumRequest_firstname_does_not_exist.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        // Получение тела ответа как строки
        String request = result.getResponse().getContentAsString();
        // Чтение ожидаемого JSON-файла
        String response = jsonFileReader.readJsonFromFile("TravelCalculatePremiumResponse_firstname_does_not_exist.json");
        assertTrue(areJsonsEqual(request,response));
    }

    @Test
    @DisplayName("Test case 2: lastName does not exist")
    public void lastNameDoesNotExist()throws Exception{
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("TravelCalculatePremiumRequest_lastname_does_not_exist.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String request = result.getResponse().getContentAsString();

        String response = jsonFileReader.readJsonFromFile("TravelCalculatePremiumResponse_lastname_does_not_exist.json");
        assertTrue(areJsonsEqual(request,response));
    }

    @Test
    @DisplayName("Test case 3: agreementDateFrom does not exist")
    public void agreementDateFromDoesNotExist()throws Exception{
        MvcResult result =  mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("TravelCalculatePremiumRequest_agreementDateFrom_does_not_exist.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String request = result.getResponse().getContentAsString();

        String response = jsonFileReader.readJsonFromFile("TravelCalculatePremiumResponse_agreementDateFrom_does_not_exist.json");
        assertTrue(areJsonsEqual(request,response));
    }

    @Test
    @DisplayName("Test case 4: agreementDateTo does not exist")
    public void agreementDateToDoesNotExist()throws Exception{
        MvcResult result =  mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("TravelCalculatePremiumRequest_agreementDateTo_does_not_exist.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String request = result.getResponse().getContentAsString();

        String response = jsonFileReader.readJsonFromFile("TravelCalculatePremiumResponse_agreementDateTo_does_not_exist.json");
        assertTrue(areJsonsEqual(request,response));
    }

    @Test
    @DisplayName("Test case 5: all fields does not exist")
    public void allFieldsDoesNotExist()throws Exception{
        MvcResult result =  mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("TravelCalculatePremiumRequest_all_fields_does_not_exist.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String request = result.getResponse().getContentAsString();

        String response = jsonFileReader.readJsonFromFile("TravelCalculatePremiumResponse_all_fields_does_not_exist.json");
        assertTrue(areJsonsEqual(request,response));
    }

    @Test
    @DisplayName("Test case 6: agreementDateTo must be after then agreement agreementDateFrom")
    public void agreementDateToMustBeAfterThenAgreementDateFrom()throws Exception{
        MvcResult result =  mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("TravelCalculatePremiumRequest_agreementDateFrom_must_be_after_then_agreementDateTo.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String request = result.getResponse().getContentAsString();

        String response = jsonFileReader.readJsonFromFile("TravelCalculatePremiumResponse_agreementDateFrom_must_be_after_then_agreementDateTo.json");
        assertTrue(areJsonsEqual(request,response));
    }

    @Test
    @DisplayName("Test case 7: all are okay")
    public void allFieldsAreCorrect()throws Exception{
        MvcResult result =  mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("TravelCalculatePremiumRequest_all_fields_are_okay.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String request = result.getResponse().getContentAsString();

        String response = jsonFileReader.readJsonFromFile("TravelCalculatePremiumResponse_all_fields_are_okay.json");
        assertTrue(areJsonsEqual(request,response));
    }

    public boolean areJsonsEqual(String json1, String json2) {
        try {
            // Прочитать JSON-строки в дерево JsonNode
            JsonNode tree1 = objectMapper.readTree(json1);
            JsonNode tree2 = objectMapper.readTree(json2);
            return tree1.equals(tree2);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка при сравнении JSON", e);
        }
    }

}