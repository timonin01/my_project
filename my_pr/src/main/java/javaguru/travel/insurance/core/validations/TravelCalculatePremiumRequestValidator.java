package javaguru.travel.insurance.core.validations;

import javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import javaguru.travel.insurance.dto.ValidationError;

import java.util.List;

public interface TravelCalculatePremiumRequestValidator {
    List<ValidationError> validate(TravelCalculatePremiumRequestV1 request);
}
