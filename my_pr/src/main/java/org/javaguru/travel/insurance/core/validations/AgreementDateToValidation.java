package org.javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.ErrorCodeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateToValidation implements TravelRequestValidation{

    private Logger logger = LoggerFactory.getLogger(AgreementDateToValidation.class);
    private final ErrorCodeUtil errorCodeUtil;


    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        //logger.info("Validation agreementDateTo");
        return (request.getAgreementDateTo() == null )
                ? Optional.of(buildError("ERROR_CODE_4"))
                : Optional.empty();
    }

    public ValidationError buildError(String errorCode){
        String description = errorCodeUtil.getErrorDescription(errorCode);
        return new ValidationError(errorCode,description);
    }
}
