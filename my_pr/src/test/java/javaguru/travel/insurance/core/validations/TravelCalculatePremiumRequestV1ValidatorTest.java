package javaguru.travel.insurance.core.validations;

import javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestV1ValidatorTest {

    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl requestValidator;

    @Test
    public void shouldNotReturnErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.empty());
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.empty());
        List<TravelRequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(requestValidator, "travelValidations", travelValidations);
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        ValidationError validationError = mock(ValidationError.class);
        when(validation1.validate(request)).thenReturn(Optional.of(validationError));
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.of(validationError));
        List<TravelRequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(requestValidator, "travelValidations", travelValidations);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 2);
    }


}
