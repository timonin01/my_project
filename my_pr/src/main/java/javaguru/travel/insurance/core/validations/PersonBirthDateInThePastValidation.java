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
public class PersonBirthDateInThePastValidation extends TravelRequestValidationImpl{

    private final DateTimeUtil dateTimeUtil;
    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (request.getPersonBirthDate()!=null && checkThatPersonBirthDateInPast(request) )
                ?Optional.of(validationErrorFactory.buildError("ERROR_CODE_12"))
                :Optional.empty();
    }

    public Boolean checkThatPersonBirthDateInPast(TravelCalculatePremiumRequestV1 request){
        Date currentDateTime = dateTimeUtil.getCurrentDateTime();
        return (currentDateTime.before(request.getPersonBirthDate()))
                ?Boolean.TRUE
                :Boolean.FALSE;
    }
}
