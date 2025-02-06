package org.javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.DateTimeService;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateFromInFutureValidation implements TravelRequestValidation {

    private Logger logger = LoggerFactory.getLogger(AgreementDateFromInFutureValidation.class);

    private final DateTimeService dateTimeService;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        //logger.info("Validation that agreementDateFrom must be in futureust be in future");
        Date dateFrom = request.getAgreementDateFrom();
        Date currentTime = dateTimeService.getCurrentDateTime();
        return (dateFrom != null && dateFrom.before(currentTime))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be in future"))
                : Optional.empty();
    }

}
