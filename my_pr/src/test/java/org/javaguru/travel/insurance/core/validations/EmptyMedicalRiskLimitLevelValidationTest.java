package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmptyMedicalRiskLimitLevelValidationTest {



    @Mock private ValidationErrorFactory validationErrorFactory;

    @InjectMocks EmptyMedicalRiskLimitLevelValidation validation;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void setUp(){
        request = mock(TravelCalculatePremiumRequest.class);
        validation.setMedicalRiskLimitLevelEnabled(true);
    }

    @Test
    public void shouldReturnErrorWhereMedicalRiskLimitLevelIsEmpty(){
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(request.getMedicalRiskLimitLevel()).thenReturn(null);
        ValidationError expectedError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_13")).thenReturn(expectedError);
        Optional<ValidationError> result = validation.validate(request);
        assertTrue(result.isPresent());
        assertEquals(expectedError, result.get());
    }

    @Test
    public void shouldNotReturnErrorWhereMedicalRiskLimitLevelIsExist(){
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(request.getMedicalRiskLimitLevel()).thenReturn("LEVEL_10000");
        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", true);
        Optional<ValidationError> errorOptional = validation.validate(request);
        assertTrue(errorOptional.isEmpty());
    }


}