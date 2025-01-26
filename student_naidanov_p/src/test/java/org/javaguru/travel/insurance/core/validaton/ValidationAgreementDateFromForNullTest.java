package org.javaguru.travel.insurance.core.validaton;

import org.assertj.core.api.Assertions;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationAgreementDateFromForNullTest {

    @Mock private TravelCalculatePremiumRequest request;

    @Test
    void shouldReturnErrorIfAgreementDateFromIsNull() {
        when(request.getAgreementDateFrom()).thenReturn(null);
        var validationAgreementDateFrom = new ValidationAgreementDateFromForNull();

        var resultOfExecute = validationAgreementDateFrom.execute(request);

        Assertions.assertThat(resultOfExecute.map(ValidationError::getField)).hasValue("agreementDateFrom");
        Assertions.assertThat(resultOfExecute.map(ValidationError::getMessage)).hasValue("Must not be null!");
    }

    @Test
    void  shouldReturnEmptyIfAgreementDateFromIsNotNull() {
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.now());
        var validationAgreementDateFrom = new ValidationAgreementDateFromForNull();

        var resultOfExecute = validationAgreementDateFrom.execute(request);

        Assertions.assertThat(resultOfExecute).isEmpty();
    }
}
