package org.javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.DateTimeService;
import org.javaguru.travel.insurance.core.ErrorCodeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateToInFutureValidation implements TravelRequestValidation{

    private Logger logger = LoggerFactory.getLogger(AgreementDateToInFutureValidation.class);
    private final ErrorCodeUtil errorCodeUtil;
    private  final DateTimeService dateTimeService;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        //logger.info("Validation agreementDateTo must be in future");
        Date dateTo = request.getAgreementDateTo();
        Date currentTime = dateTimeService.getCurrentDateTime();
        return (dateTo != null && dateTo.before(currentTime))
                ? Optional.of(buildError("ERROR_CODE_3"))
                : Optional.empty();
    }

    public ValidationError buildError(String errorCode){
        String description = errorCodeUtil.getErrorDescription(errorCode);
        return new ValidationError(errorCode,description);
    }
}
