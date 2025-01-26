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
class ValidationPersonLastNameForNullTest {
    @Mock
    TravelCalculatePremiumRequest request;

    @Test
    void ifLastNameIsEmptyShouldReturnError() {
        when(request.getPersonLastName()).thenReturn("");
        var validationPersonLastName = new ValidationPersonLastNameForNull();

        var validationError = validationPersonLastName.execute(request);

        Assertions.assertThat(validationError.map(ValidationError::getField)).hasValue("personLastName");
        Assertions.assertThat(validationError.map(ValidationError::getMessage)).hasValue("Must not be empty!");
    }

    @Test
    void ifLasttNameIsNotNullOrEmptyShouldReturnEmpty() {
        when(request.getPersonLastName()).thenReturn("John");
        var validationPersonLastName = new ValidationPersonLastNameForNull();

        var validationError = validationPersonLastName.execute(request);

        Assertions.assertThat(validationError).isEmpty();
    }

    @Test
    void ifLastNameIsNullShouldReturnError() {
        when(request.getPersonLastName()).thenReturn(null);
        var validationPersonLastName = new ValidationPersonLastNameForNull();

        var validationError = validationPersonLastName.execute(request);

        Assertions.assertThat(validationError.map(ValidationError::getField)).hasValue("personLastName");
        Assertions.assertThat(validationError.map(ValidationError::getMessage)).hasValue("Must not be empty!");
    }

}
