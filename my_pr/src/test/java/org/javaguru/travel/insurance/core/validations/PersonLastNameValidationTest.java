package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.ErrorCodeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
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

    @Mock private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private PersonLastNameValidation validation;

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn(null);
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_8"))
                .thenReturn("Field personLastName must not be empty!");
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getErrorCode(), "ERROR_CODE_8");
        assertEquals(error.get().getDescription(), "Field personLastName must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("");
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_8"))
                .thenReturn("Field personLastName must not be empty!");
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getErrorCode(), "ERROR_CODE_8");
        assertEquals(error.get().getDescription(), "Field personLastName must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIsCorrect(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("Andrey");
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isEmpty());
    }
}