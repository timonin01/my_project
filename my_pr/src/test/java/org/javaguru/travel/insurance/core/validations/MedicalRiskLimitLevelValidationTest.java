package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicalRiskLimitLevelValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;
    @Mock
    private ClassifierValueRepository classifierValueRepository;

    @InjectMocks
    private MedicalRiskLimitLevelValidation validation;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void setUp() {
        request = mock(TravelCalculatePremiumRequest.class);
        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", true);
    }

    @Test
    public void shouldReturnError() {
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(request.getMedicalRiskLimitLevel()).thenReturn("LEVEL_10000");
        when(classifierValueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", "LEVEL_10000"))
                .thenReturn(Optional.empty());
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_14")).thenReturn(validationError);
        Optional<ValidationError> error = validation.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationError, error.get());
    }

    @Test
    public void shouldNotReturnErrorWhereMedicalRiskLimitLevelIsExist() {
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(request.getMedicalRiskLimitLevel()).thenReturn("LEVEL_10000");
        ClassifierValue classifierValue = mock(ClassifierValue.class);
        when(classifierValueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", "LEVEL_10000"))
                .thenReturn(Optional.of(classifierValue));
        Optional<ValidationError> validationErrorOpt = validation.validate(request);
        assertTrue(validationErrorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }

    @Test
    public void shouldNotReturnErrorWhereMedicalRiskLimitLevelIsNull() {
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(request.getMedicalRiskLimitLevel()).thenReturn(null);
        Optional<ValidationError> error = validation.validate(request);
        assertTrue(error.isEmpty());
    }
    @Test
    public void shouldNotReturnErrorWhereMedicalRiskLimitLevelIsBlank() {
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(request.getMedicalRiskLimitLevel()).thenReturn("");
        Optional<ValidationError> error = validation.validate(request);
        assertTrue(error.isEmpty());
    }

}