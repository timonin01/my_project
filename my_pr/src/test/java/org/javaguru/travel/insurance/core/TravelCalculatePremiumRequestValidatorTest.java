package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.core.validations.*;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {

    @Mock private PersonFirstNameValidation personFirstNameValidation;
    @Mock private PersonLastNameValidation personLastNameValidation;
    @Mock private AgreementDateFromValidation agreementDateFromValidation;
    @Mock private AgreementDateToValidation agreementDateToValidation;
    @Mock private AgreementDateToMustBeAfterThenAgreementDateFrom agreementDateToMustBeAfterThenAgreementDateFrom;
    @Mock private AgreementDateFromInFutureValidation agreementDateFromInFutureValidation;
    @Mock private AgreementDateToInFutureValidation agreementDateToInFutureValidation;

    @InjectMocks
    private TravelCalculatePremiumRequestValidator requestValidator;

    @Test
    public void shouldSucceed() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(personFirstNameValidation.validatePersonFirstName(request)).thenReturn(Optional.empty());
        when(personLastNameValidation.validatePersonLastName(request)).thenReturn(Optional.empty());
        when(agreementDateFromValidation.validateAgreementDateFrom(request)).thenReturn(Optional.empty());
        when(agreementDateToValidation.validateAgreementDateTo(request)).thenReturn(Optional.empty());
        when(agreementDateFromInFutureValidation.validateAgreementDateFromInFuture(request)).thenReturn(Optional.empty());
        when(agreementDateFromInFutureValidation.validateAgreementDateFromInFuture(request)).thenReturn(Optional.empty());
        when(agreementDateToInFutureValidation.validateAgreementDateToInFuture(request)).thenReturn(Optional.empty());
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(personFirstNameValidation.validatePersonFirstName(request)).thenReturn(Optional.of(new ValidationError()));
        when(personLastNameValidation.validatePersonLastName(request)).thenReturn(Optional.of(new ValidationError()));
        when(agreementDateFromValidation.validateAgreementDateFrom(request)).thenReturn(Optional.of(new ValidationError()));
        when(agreementDateToValidation.validateAgreementDateTo(request)).thenReturn(Optional.of(new ValidationError()));
        when(agreementDateFromInFutureValidation.validateAgreementDateFromInFuture(request)).thenReturn(Optional.of(new ValidationError()));
        when(agreementDateToMustBeAfterThenAgreementDateFrom.validateAgreementDaysBetween(request)).thenReturn(Optional.of(new ValidationError()));
        when(agreementDateToInFutureValidation.validateAgreementDateToInFuture(request)).thenReturn(Optional.of(new ValidationError()));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 7);
    }


}
