package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AgreementDateToValidationTest {

    private AgreementDateToValidation validation;

    @BeforeEach
    public void setUp(){
        validation = new AgreementDateToValidation();
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> error = validation.validateAgreementDateTo(request);
        assertEquals(error.get().getField(), "agreementDateTo");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateToIsCorrect(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("05.02.2025"));
        Optional<ValidationError> error = validation.validateAgreementDateTo(request);
        assertTrue(error.isEmpty());
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}