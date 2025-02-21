package org.javaguru.travel.insurance.core.validations.agreement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateToMustBeAfterThenAgreementDateFrom extends TravelAgreementFieldValidationImpl {

    private Logger logger = LoggerFactory.getLogger(AgreementDateToMustBeAfterThenAgreementDateFrom.class);
    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        //logger.info("Validation that AgreementDateToMustBeAfterThenAgreementDateFrom must " +
        //        "be after then agreementDateFrom");
        Date dateFrom = agreement.getAgreementDateFrom();
        Date dateTo = agreement.getAgreementDateTo();
        return (dateFrom != null && dateTo != null //чтобы не проверять на наличие null
                && (dateFrom.equals(dateTo) || dateFrom.after(dateTo)))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_5"))
                : Optional.empty();
    }
}
