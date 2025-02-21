package org.javaguru.travel.insurance.core.validations.agreement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateToInFutureValidation extends TravelAgreementFieldValidationImpl {

    private Logger logger = LoggerFactory.getLogger(AgreementDateToInFutureValidation.class);
    private final ValidationErrorFactory validationErrorFactory;
    private  final DateTimeUtil dateTimeService;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        //logger.info("Validation agreementDateTo must be in future");
        Date dateTo = agreement.getAgreementDateTo();
        Date currentTime = dateTimeService.getCurrentDateTime();
        return (dateTo != null && dateTo.before(currentTime))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_3"))
                : Optional.empty();
    }
}
