package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.DateTimeService;
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
class AgreementDateToInFutureValidationTest {

    @Mock private DateTimeService dateTimeService;
    @Mock private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private AgreementDateToInFutureValidation validation;

    @BeforeEach
    public void setUp(){
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("05.02.2025"));
    }


    @Test
    public void shouldReturnErrorWhenAgreementDateToInPast(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2023"));
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_3"))
                .thenReturn("Field agreementDateTo must be in future!");
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getErrorCode(), "ERROR_CODE_3");
        assertEquals(error.get().getDescription(), "Field agreementDateTo must be in future!");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateToIsCorrect(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("05.02.2025"));
        Optional<ValidationError> error = validation.execute(request);
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