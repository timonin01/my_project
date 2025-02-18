package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.javaguru.travel.insurance.dto.ValidationError;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonBirthDateInThePastValidationTest {

    @Mock private DateTimeUtil dateTimeUtil;
    @Mock private ValidationErrorFactory  validationErrorFactory;

    @InjectMocks PersonBirthDateInThePastValidation validation;


    @Test
    public void shouldNotReturnErrorWhenPersonBirthDateInPast(){
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonBirthDate()).thenReturn(createDate("08.12.2006"));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(createDate("01.01.2026"));
        Optional<ValidationError> error = validation.validate(request);
        assertTrue(error.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenPersonBirthDateInFuture(){
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonBirthDate()).thenReturn(createDate("01.01.2029"));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(createDate("14.02.2025"));
        ValidationError validationError = mock(ValidationError.class);
        lenient().when(validationErrorFactory.buildError("ERROR_CODE_12"))
                .thenReturn(validationError);
        Optional<ValidationError> error = validation.validate(request);
        assertTrue(error.isPresent());
        assertSame(error.get(), validationError);
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}