package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class AgreementDateToMustBeAfterThenAgreementDateFrom implements TravelRequestValidation{

    private Logger logger = LoggerFactory.getLogger(AgreementDateToMustBeAfterThenAgreementDateFrom.class);

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        //logger.info("Validation that AgreementDateToMustBeAfterThenAgreementDateFrom must " +
        //        "be after then agreementDateFrom");
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        return (dateFrom != null && dateTo != null //чтобы не проверять на наличие null
                && (dateFrom.equals(dateTo) || dateFrom.after(dateTo)))
                ? Optional.of(new ValidationError("agreementDateTo", "Must be after then agreementDateFrom"))
                : Optional.empty();
    }
}
