package org.javaguru.travel.insurance.core.messagebroker.proposalack;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;

public interface ProposalGeneratorQueueSender {
    void send(AgreementDTO agreement);
}
