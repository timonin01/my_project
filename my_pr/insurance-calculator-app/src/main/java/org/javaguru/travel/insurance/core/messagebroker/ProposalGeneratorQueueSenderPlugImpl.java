package  org.javaguru.travel.insurance.core.messagebroker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"h2", "mysql-local"})
public class ProposalGeneratorQueueSenderPlugImpl implements ProposalGeneratorQueueSender{

    private final Logger logger = LoggerFactory.getLogger(ProposalGeneratorQueueSenderPlugImpl.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void send(AgreementDTO agreement) {
        try {
            // Преобразуем объект в JSON строку
            String jsonString = objectMapper.writeValueAsString(agreement);

            // Логируем JSON строку
            logger.info("Agreeent as JSON: {}", jsonString);

        } catch (Exception e) {
            logger.error("Error to convert agreement to JSON", e);
        }
    }
}
