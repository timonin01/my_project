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

class AgreementDateFromValidationTest {

    private AgreementDateFromValidation validation;

    @BeforeEach
    public void setUp(){
        validation = new AgreementDateFromValidation();
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateFromIsNull(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> error = validation.validateAgreementDateFrom(request);
        assertEquals(error.get().getField(),"agreementDateFrom");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromIsCorrect(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("05.02.2025"));
        Optional<ValidationError> error = validation.validateAgreementDateFrom(request);
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