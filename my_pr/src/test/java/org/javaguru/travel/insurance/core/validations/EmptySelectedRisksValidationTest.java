package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmptySelectedRisksValidationTest {

    private EmptySelectedRisksValidation validation;

    @BeforeEach
    public void setUp(){
        validation = new EmptySelectedRisksValidation();
    }

    @Test
    public void shouldReturnErrorWhenSelectedRisksIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(null);
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(),"selectedRisks");
        assertEquals(error.get().getMessage(),"Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenSelectedRisksIsEmpty(){
        TravelCalculatePremiumRequest request  = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of());
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(),"selectedRisks");
        assertEquals(error.get().getMessage(),"Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenSelectedRisksExist(){
        TravelCalculatePremiumRequest request  = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION",
                "TRAVEL_LOSS_BAGGAGE","TRAVEL_THIRD_PARTY_LIABILITY",
                "TRAVEL_EVACUATION", "TRAVEL_SPORT_ACTIVITIES"));
        Optional<ValidationError> error = validation.execute(request);
        assertFalse(error.isPresent());
        assertTrue(error.isEmpty());
    }

}