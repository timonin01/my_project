package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {


    @Mock private DateTimeService dateTimeService;

    @InjectMocks
    private TravelCalculatePremiumRequestValidator requestValidator;


    @BeforeEach
    public void setUp(){
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2025"));
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("Timonin");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2026"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2026"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("Timonin");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2026"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2026"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Andrey");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2026"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2026"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Andrey");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2026"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2026"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }


    @Test
    public void shouldReturnErrorWhenAgreementDateFromIsNull(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Andrey");
        when(request.getPersonLastName()).thenReturn("Timonin");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2026"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"agreementDateFrom");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Andrey");
        when(request.getPersonLastName()).thenReturn("Timonin");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2026"));
        when(request.getAgreementDateTo()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "agreementDateTo");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToBeforeThenDateFrom(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Andrey");
        when(request.getPersonLastName()).thenReturn("Timonin");
        when(request.getAgreementDateFrom()).thenReturn(createDate("10.01.2026"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2026"));
        List<ValidationError> errors= requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "agreementDateTo");
        assertEquals(errors.get(0).getMessage(), "Must be after then agreementDateFrom");

    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToEqualsDateFrom(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Andrey");
        when(request.getPersonLastName()).thenReturn("Timonin");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2026"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2026"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(), "agreementDateTo");
        assertEquals(errors.get(0).getMessage(), "Must be after then agreementDateFrom");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateToAfterThenDateFrom(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Andrey");
        when(request.getPersonLastName()).thenReturn("Timonin");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2026"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2026"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateFromInPast(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Andrey");
        when(request.getPersonLastName()).thenReturn("Timonin");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2026"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(), "agreementDateFrom");
        assertEquals(errors.get(0).getMessage(), "Must be in future");
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToInPast(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Andrey");
        when(request.getPersonLastName()).thenReturn("Timonin");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2026"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(),2);
        assertEquals(errors.get(1).getField(), "agreementDateTo");
        assertEquals(errors.get(1).getMessage(), "Must be in future");
    }


    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
