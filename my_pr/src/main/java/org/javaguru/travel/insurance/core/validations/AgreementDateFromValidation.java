package org.javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.javaguru.travel.insurance.core.ErrorCodeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateFromValidation implements TravelRequestValidation{

    private final ErrorCodeUtil errorCodeUtil ;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null )
                ? Optional.of(buildError("ERROR_CODE_2"))
                : Optional.empty();
    }

    public ValidationError buildError(String errorCode){
        String description = errorCodeUtil.getErrorDescription(errorCode);
        return new ValidationError(errorCode,description);
    }
}
