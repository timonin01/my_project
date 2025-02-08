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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgreementDateToMustBeAfterThenAgreementDateFromTest {

    @Mock private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private AgreementDateToMustBeAfterThenAgreementDateFrom validation;


    @Test
    public void shouldReturnErrorWhenAgreementDateToBeforeThenDateFrom(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("10.01.2026"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2026"));
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_5"))
                .thenReturn("Field agreementDateFrom must be after then agreementDateFrom");
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getErrorCode(), "ERROR_CODE_5");
        assertEquals(error.get().getDescription(), "Field agreementDateFrom must be after then agreementDateFrom");

    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToEqualsDateFrom(){
        TravelCalculatePremiumRequest request= mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2026"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2026"));
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_5"))
                .thenReturn("Field agreementDateFrom must be after then agreementDateFrom");
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getErrorCode(), "ERROR_CODE_5");
        assertEquals(error.get().getDescription(), "Field agreementDateFrom must be after then agreementDateFrom");
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