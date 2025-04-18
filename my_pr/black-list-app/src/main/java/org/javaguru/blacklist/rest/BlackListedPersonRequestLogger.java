package org.javaguru.blacklist.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.blacklist.dto.BlackListedPersonCheckRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BlackListedPersonRequestLogger {

    private final Logger logger = LoggerFactory.getLogger(BlackListedPersonRequestLogger.class);

    public void log(BlackListedPersonCheckRequest request){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(request);
            // Логирование JSON-строки
            logger.info("REQUEST: " + jsonRequest);
        } catch (Exception e) {
            logger.error("ERROR TO CONVERT TO JSON", e);
        }
    }

}
