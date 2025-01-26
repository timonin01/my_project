package org.javaguru.travel.insurance.core.validaton;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Optional;

@Component
class ValidationAgreementDateToIsFuture implements Validation {
    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() != null && request.getAgreementDateTo().isBefore(LocalDate.now()))
                ? Optional.of(new ValidationError("agreementDateTo", "Must be in the future!"))
                : Optional.empty();
    }
}
