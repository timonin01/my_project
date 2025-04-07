package org.javaguru.travel.insurance.rest.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestLoggerV2 {

    private final Logger logger  = LoggerFactory.getLogger(TravelCalculatePremiumRequestLoggerV2.class);

    public void log(TravelCalculatePremiumRequestV2 request){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(request);
            // Логирование JSON-строки
            logger.info("REQUEST: " + jsonRequest);
        } catch (Exception e) {
            logger.error("Ошибка при преобразовании запроса в JSON", e);
        }
    }

}
