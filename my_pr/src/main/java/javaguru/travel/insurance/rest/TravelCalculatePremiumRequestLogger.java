package javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestLogger {

    private final Logger logger  = LoggerFactory.getLogger(TravelCalculatePremiumRequestLogger.class);

    public void log(TravelCalculatePremiumRequestV1 request){
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
