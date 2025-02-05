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

class AgreementDateToMustBeAfterThenAgreementDateFromTest {

    private AgreementDateToMustBeAfterThenAgreementDateFrom validation;

    @BeforeEach
    public void setUp(){
        validation = new AgreementDateToMustBeAfterThenAgreementDateFrom();
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToBeforeThenDateFrom(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("10.01.2026"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2026"));
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "agreementDateTo");
        assertEquals(error.get().getMessage(), "Must be after then agreementDateFrom");

    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToEqualsDateFrom(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2026"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2026"));
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "agreementDateTo");
        assertEquals(error.get().getMessage(), "Must be after then agreementDateFrom");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateToAfterThenDateFrom(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2026"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2026"));
        Optional<ValidationError> error = validation.execute(request);
        assertFalse(error.isPresent());
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}