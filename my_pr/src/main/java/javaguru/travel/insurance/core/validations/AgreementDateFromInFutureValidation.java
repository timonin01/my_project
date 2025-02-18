package javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import javaguru.travel.insurance.core.util.DateTimeUtil;
import javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateFromInFutureValidation extends TravelRequestValidationImpl {

    private final DateTimeUtil dateTimeService;
    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date currentTime = dateTimeService.getCurrentDateTime();
        return (dateFrom != null && dateFrom.before(currentTime))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_1"))
                : Optional.empty();
    }


}
