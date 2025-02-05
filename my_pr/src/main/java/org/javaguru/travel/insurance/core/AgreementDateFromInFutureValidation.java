package org.javaguru.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AgreementDateFromInFutureValidation {

    private final DateTimeService dateTimeService;

    public Optional<ValidationError> validateAgreementDateFromInFuture(TravelCalculatePremiumRequest request){
        Date dateFrom = request.getAgreementDateFrom();
        Date currentTime = dateTimeService.getCurrentDateTime();
        return (dateFrom != null && dateFrom.before(currentTime))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be in future"))
                : Optional.empty();
    }
}
