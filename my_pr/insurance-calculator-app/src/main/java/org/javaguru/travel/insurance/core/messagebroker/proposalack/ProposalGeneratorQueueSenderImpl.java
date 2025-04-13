package org.javaguru.travel.insurance.core.messagebroker.proposalack;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.messagebroker.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Profile("mysql-container")
public class ProposalGeneratorQueueSenderImpl implements ProposalGeneratorQueueSender{

    private final Logger logger = LoggerFactory.getLogger(ProposalGeneratorQueueSenderImpl.class);

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void send(AgreementDTO agreement) {
        try {
            // Преобразуем объект в JSON строку
            String jsonString = objectMapper.writeValueAsString(agreement);
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_PROPOSAL_GENERATION, jsonString);
            // Логируем JSON строку
            logger.info("Agreeent as JSON: {}", jsonString);
        }catch (AmqpException e) {
                logger.error("Failed to send message to RabbitMQ", e);
        } catch (Exception e) {
            logger.error("Error to convert agreement to JSON", e);
        }
    }
}
