package  org.javaguru.travel.insurance.core.validations.agreement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.javaguru.travel.insurance.core.validations.agreement.TravelAgreementFieldValidationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateToValidation extends TravelAgreementFieldValidationImpl {

    private Logger logger = LoggerFactory.getLogger(AgreementDateToValidation.class);
    private final ValidationErrorFactory validationErrorFactory;


    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        //logger.info("Validation agreementDateTo");
        return (agreement.getAgreementDateTo() == null )
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_4"))
                : Optional.empty();
    }
}
