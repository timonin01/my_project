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
class ValidationAgreementDateToForNullTest {

    @Mock private TravelCalculatePremiumRequest request;

    @Test
    void ifAgreementDateToIsNullShouldReturnError() {
        when(request.getAgreementDateTo()).thenReturn(null);
        ValidationAgreementDateToForNull validator = new ValidationAgreementDateToForNull();

        var result = validator.execute(request);

        Assertions.assertThat(result.map(ValidationError::getField)).hasValue("agreementDateTo");
        Assertions.assertThat(result.map(ValidationError::getMessage)).hasValue("Must not be null!");
    }

    @Test
    void ifAgreementDateToIsNotNullShouldReturnEmpty() {
        when(request.getAgreementDateTo()).thenReturn(LocalDate.now());

        ValidationAgreementDateToForNull validator = new ValidationAgreementDateToForNull();
        var result = validator.execute(request);

        Assertions.assertThat(result).isEmpty();
    }
}
