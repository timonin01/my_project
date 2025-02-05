package org.javaguru.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumRequestValidator {

    private final DateTimeService dateTimeService;
    private final PersonFirstNameValidation personFirstNamValidation;
    private final PersonLastNameValidation personLastNameValidation;
    private final AgreementDateFromValidation agreementDateFromValidation;
    private final AgreementDateToValidation agreementDateToValidation;
    private final AgreementDateFromInFutureValidation agreementDateFromInFutureValidation;
    private final AgreementDateToInFutureValidation agreementDateToInFutureValidation;
    private final AgreementDateToMustBeAfterThenAgreementDateFrom agreementDateToMustBeAfterThenAgreementDateFrom;



    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        personFirstNamValidation.validatePersonFirstName(request).ifPresent(errors::add);
        personLastNameValidation.validatePersonLastName(request).ifPresent(errors::add);
        agreementDateFromValidation.validateAgreementDateFrom(request).ifPresent(errors::add);
        agreementDateToValidation.validateAgreementDateTo(request).ifPresent(errors::add);
        agreementDateToMustBeAfterThenAgreementDateFrom.validateAgreementDaysBetween(request).ifPresent(errors::add);
        agreementDateFromInFutureValidation.validateAgreementDateFromInFuture(request).ifPresent(errors::add);
        agreementDateToInFutureValidation.validateAgreementDateToInFuture(request).ifPresent(errors::add);
        return errors;
    }


}
