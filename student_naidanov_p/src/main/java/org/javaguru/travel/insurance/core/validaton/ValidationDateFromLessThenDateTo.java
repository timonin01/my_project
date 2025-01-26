package org.javaguru.travel.insurance.core.validaton;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
class ValidationDateFromLessThenDateTo implements Validation{
    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        LocalDate dateFrom = request.getAgreementDateFrom();
        LocalDate dateTo = request.getAgreementDateTo();
        return (dateFrom != null && dateTo != null
                && (dateFrom.equals(dateTo) || dateFrom.isAfter(dateTo)))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be less than agreementDateTo!"))
                : Optional.empty();
    }

}
