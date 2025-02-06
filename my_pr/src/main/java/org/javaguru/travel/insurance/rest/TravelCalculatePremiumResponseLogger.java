package org.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumResponseLogger {
    private final Logger logger  = LoggerFactory.getLogger(TravelCalculatePremiumResponseLogger.class);

    public void log(TravelCalculatePremiumResponse response){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(response);
            // Логирование JSON-строки
            logger.info("RESPONSE: " + jsonRequest);
        } catch (Exception e) {
            logger.error("Ошибка при преобразовании запроса в JSON", e);
        }
    }
}
