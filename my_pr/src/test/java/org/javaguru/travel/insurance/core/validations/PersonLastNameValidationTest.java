package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonLastNameValidationTest {

    @Mock private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private PersonLastNameValidation validation;

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_8"))
                .thenReturn(validationError);
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertSame(error.get(), validationError);
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("");
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_8"))
                .thenReturn(validationError);
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertSame(error.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIsCorrect(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("Andrey");
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isEmpty());
    }
}