package org.javaguru.blacklist.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.blacklist.dto.BlackListedPersonCheckRequest;
import org.javaguru.blacklist.dto.BlackListedPersonCheckResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BlackListedPersonResponseLogger {

    private final Logger logger = LoggerFactory.getLogger(BlackListedPersonResponseLogger.class);

    public void log(BlackListedPersonCheckResponse response){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(response);
            // Логирование JSON-строки
            logger.info("RESPONSE: " + jsonRequest);
        } catch (Exception e) {
            logger.error("ERROR TO CONVERT TO JSON", e);
        }
    }

}
