package javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class PersonLastNameValidation extends TravelRequestValidationImpl {

    private Logger logger = LoggerFactory.getLogger(PersonLastNameValidation.class);
    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        //logger.info("Validation PersonLastname");
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_8"))
                : Optional.empty();
    }

}
