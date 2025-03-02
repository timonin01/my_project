package org.javaguru.travel.insurance.rest.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelGetAgreementRequestLogger {

    private final Logger logger  = LoggerFactory.getLogger(TravelGetAgreementRequestLogger.class);

    public void log(String uuid){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(uuid);
            // Логирование JSON-строки
            logger.info("REQUEST: " + jsonRequest);
        } catch (Exception e) {
            logger.error("Ошибка при преобразовании запроса в JSON", e);
        }
    }

}
