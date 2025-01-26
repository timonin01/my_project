package org.javaguru.travel.insurance.core.validaton;

import java.util.List;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

@Component
public interface RequestValidator {
    List<ValidationError> validateRequest(TravelCalculatePremiumRequest request);
}
