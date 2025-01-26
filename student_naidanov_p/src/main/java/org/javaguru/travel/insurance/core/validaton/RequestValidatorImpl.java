package org.javaguru.travel.insurance.core.validaton;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class RequestValidatorImpl implements RequestValidator {

    private final List<Validation> validations;

    public List<ValidationError> validateRequest(TravelCalculatePremiumRequest request) {
        return validations.stream()
            .map(validation -> validation.execute(request))
            .filter(Optional::isPresent)
            .map(Optional::get).collect(
                Collectors.toList());
    }

}
