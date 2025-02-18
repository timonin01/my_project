package javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class EmptyCountryValidation extends TravelRequestValidationImpl{

    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return ((request.getCountry() == null || request.getCountry().isBlank()))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_10"))
                : Optional.empty();
    }
}
