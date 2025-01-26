package org.javaguru.travel.insurance.core.validaton;

import org.assertj.core.api.Assertions;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ValidationPersonFirstNameForNullTest {
    @Mock
    TravelCalculatePremiumRequest request;

    @Test
    void ifFirstNameIsEmptyShouldReturnError() {
        when(request.getPersonFirstName()).thenReturn("");
        ValidationPersonFirstNameForNull validationPersonFirstNameForNull = new ValidationPersonFirstNameForNull();

        var validationError = validationPersonFirstNameForNull.execute(request);

        Assertions.assertThat(validationError.map(ValidationError::getField)).hasValue("personFirstName");
        Assertions.assertThat(validationError.map(ValidationError::getMessage)).hasValue("Must not be empty!");
    }

    @Test
    void ifFirstNameIsNotNullOrEmptyShouldReturnEmpty() {
        when(request.getPersonFirstName()).thenReturn("John");
        ValidationPersonFirstNameForNull validationPersonFirstNameForNull = new ValidationPersonFirstNameForNull();

        var validationError = validationPersonFirstNameForNull.execute(request);

        Assertions.assertThat(validationError).isEmpty();
    }

    @Test
    void ifFirstNameIsNullShouldReturnError() {
        when(request.getPersonFirstName()).thenReturn(null);
        ValidationPersonFirstNameForNull validationPersonFirstNameForNull = new ValidationPersonFirstNameForNull();

        var validationError = validationPersonFirstNameForNull.execute(request);

        Assertions.assertThat(validationError.map(ValidationError::getField)).hasValue("personFirstName");
        Assertions.assertThat(validationError.map(ValidationError::getMessage)).hasValue("Must not be empty!");
    }
}
