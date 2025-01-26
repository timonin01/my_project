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
class ValidationDateFromLessThenDateToTest {
    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    void ifDateFromNotLessThenDateToShouldReturnError() {
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.now().plusDays(1));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.now());
        ValidationDateFromLessThenDateTo validator = new ValidationDateFromLessThenDateTo();

        var result = validator.execute(request);

        Assertions.assertThat(result.map(ValidationError::getField)).hasValue("agreementDateFrom");
        Assertions.assertThat(result.map(ValidationError::getMessage)).hasValue("Must be less than agreementDateTo!");

    }

    @Test
    void ifDateFromLessThenDateToShouldReturnEmpty() {
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.now());
        when(request.getAgreementDateTo()).thenReturn(LocalDate.now().plusDays(1));
        ValidationDateFromLessThenDateTo validator = new ValidationDateFromLessThenDateTo();

        var result = validator.execute(request);

        Assertions.assertThat(result).isEmpty();

    }
}