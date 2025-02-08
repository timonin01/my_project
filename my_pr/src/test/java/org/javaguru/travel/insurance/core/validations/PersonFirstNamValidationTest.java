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
class PersonFirstNamValidationTest {

    @Mock private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private PersonFirstNameValidation validation;

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_7"))
                .thenReturn("Field personFirstName must not be empty!");
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getErrorCode(), "ERROR_CODE_7");
        assertEquals(error.get().getDescription(), "Field personFirstName must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_7"))
                .thenReturn("Field personFirstName must not be empty!");
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getErrorCode(), "ERROR_CODE_7");
        assertEquals(error.get().getDescription(), "Field personFirstName must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenPersonFirstCorrect() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Andrey");
        Optional<ValidationError> error = validation.execute(request);
        assertFalse(error.isPresent());
        assertTrue(error.isEmpty());
    }





}