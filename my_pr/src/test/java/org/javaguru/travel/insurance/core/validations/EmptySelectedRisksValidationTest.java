package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmptySelectedRisksValidationTest {

    @Mock private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private EmptySelectedRisksValidation validation;

    @Test
    public void shouldReturnErrorWhenSelectedRisksIsNull(){
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getSelectedRisks()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_6"))
                .thenReturn(validationError);
        Optional<ValidationError> error = validation.validate(request);
        assertTrue(error.isPresent());
        assertSame(error.get(), validationError);
    }

    @Test
    public void shouldReturnErrorWhenSelectedRisksIsEmpty(){
        TravelCalculatePremiumRequestV1 request  = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getSelectedRisks()).thenReturn(List.of());
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_6"))
                .thenReturn(validationError);
        Optional<ValidationError> error = validation.validate(request);
        assertTrue(error.isPresent());
        assertSame(error.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorWhenSelectedRisksExist(){
        TravelCalculatePremiumRequestV1 request  = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION",
                "TRAVEL_LOSS_BAGGAGE","TRAVEL_THIRD_PARTY_LIABILITY",
                "TRAVEL_EVACUATION", "TRAVEL_SPORT_ACTIVITIES"));
        Optional<ValidationError> error = validation.validate(request);
        assertFalse(error.isPresent());
    }

}