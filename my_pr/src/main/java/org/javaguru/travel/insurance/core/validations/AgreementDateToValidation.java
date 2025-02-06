package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class AgreementDateToValidation implements TravelRequestValidation{

    private Logger logger = LoggerFactory.getLogger(AgreementDateToValidation.class);

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        //logger.info("Validation agreementDateTo");
        return (request.getAgreementDateTo() == null )
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"))
                : Optional.empty();
    }
}
