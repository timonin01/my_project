package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonFirstNamValidationTest {

    private PersonFirstNameValidation validation;

    @BeforeEach
    public void setUp(){
        validation = new PersonFirstNameValidation();
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "personFirstName");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "personFirstName");
        assertEquals(error.get().getMessage(), "Must not be empty!");
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