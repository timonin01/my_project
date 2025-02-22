package org.javaguru.travel.insurance.rest.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumResponseLoggerV2 {
    private final Logger logger  = LoggerFactory.getLogger(TravelCalculatePremiumResponseLoggerV2.class);

    public void log(TravelCalculatePremiumResponseV2 response){
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
