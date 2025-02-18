package javaguru.travel.insurance.core.validations;

import javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonFirstNamValidationTest {

    @Mock private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private PersonFirstNameValidation validation;

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsNull(){
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonFirstName()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_7"))
                .thenReturn(validationError);
        Optional<ValidationError> error = validation.validate(request);
        assertTrue(error.isPresent());
        assertSame(error.get(), validationError);
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonFirstName()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_7"))
                .thenReturn(validationError);
        Optional<ValidationError> error = validation.validate(request);
        assertTrue(error.isPresent());
        assertSame(error.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorWhenPersonFirstCorrect() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonFirstName()).thenReturn("Andrey");
        Optional<ValidationError> error = validation.validate(request);
        assertFalse(error.isPresent());
        assertTrue(error.isEmpty());
    }





}