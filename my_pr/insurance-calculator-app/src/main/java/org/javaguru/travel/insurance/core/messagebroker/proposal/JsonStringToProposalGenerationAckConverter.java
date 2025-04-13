package org.javaguru.travel.insurance.core.messagebroker.proposal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class JsonStringToProposalGenerationAckConverter {

    private static final Logger logger = LoggerFactory.getLogger(JsonStringToProposalGenerationAckConverter.class);

    public ProposalGenerationAck convert(String json) throws JsonProcessingException {
        logger.info("Converting JSON: {}", json);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, ProposalGenerationAck.class);
    }

}
