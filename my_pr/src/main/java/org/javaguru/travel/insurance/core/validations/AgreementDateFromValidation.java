package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class AgreementDateFromValidation implements TravelRequestValidation{

    private Logger logger = LoggerFactory.getLogger(AgreementDateFromValidation.class);

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        //logger.info("Validation agreementDateFrom");
        return (request.getAgreementDateFrom() == null )
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be empty!"))
                : Optional.empty();
    }
}
