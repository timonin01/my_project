package org.javaguru.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class AgreementDateToMustBeAfterThenAgreementDateFrom {

    public Optional<ValidationError> validateAgreementDaysBetween(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        return (dateFrom != null && dateTo != null //чтобы не проверять на наличие null
                && (dateFrom.equals(dateTo) || dateFrom.after(dateTo)))
                ? Optional.of(new ValidationError("agreementDateTo", "Must be after then agreementDateFrom"))
                : Optional.empty();
    }
}
