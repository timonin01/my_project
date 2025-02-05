package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonLastNameValidationTest {

    private PersonLastNameValidation validation;

    @BeforeEach
    public void setUp(){
        validation = new PersonLastNameValidation();
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn(null);
        Optional<ValidationError> error = validation.validatePersonLastName(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "personLastName");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("");
        Optional<ValidationError> error = validation.validatePersonLastName(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "personLastName");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIsCorrect(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("Andrey");
        Optional<ValidationError> error = validation.validatePersonLastName(request);
        assertTrue(error.isEmpty());
    }
}