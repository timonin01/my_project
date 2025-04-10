package org.javaguru.doc.generator.core.messagebroker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.doc.generator.core.api.dto.AgreementDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class JsonStringToAgreementDtoConverter {

    private static final Logger logger = LoggerFactory.getLogger(JsonStringToAgreementDtoConverter.class);

    public AgreementDTO convert(String json) throws JsonProcessingException {
        logger.info("Converting JSON: {}", json);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, AgreementDTO.class);
    }

}
