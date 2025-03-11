package org.javaguru.travel.insurance.rest.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.common.JsonFileReader;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompareJSONV1 {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private JsonFileReader jsonFileReader;

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/insurance/travel/api/v1/";

    public void comparingJSON(String path1,String path2) throws Exception{
        MvcResult result =mockMvc.perform(post(BASE_URL)
                        .content(jsonFileReader.readJsonFromFile(path1))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String request = result.getResponse().getContentAsString();

        String response = jsonFileReader.readJsonFromFile(path2);

        //игнорирование UUID
        CustomComparator customComparator = new CustomComparator(
                JSONCompareMode.NON_EXTENSIBLE,
                new Customization("uuid", (o1, o2) -> true) // Игнорируем uuid
        );

        //порядок не важен
        JSONAssert.assertEquals(response, request, customComparator);
    }

}
