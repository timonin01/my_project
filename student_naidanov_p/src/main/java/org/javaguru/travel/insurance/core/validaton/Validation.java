package org.javaguru.travel.insurance.core.validaton;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public interface Validation {
    Optional<ValidationError> execute(TravelCalculatePremiumRequest request);
}
