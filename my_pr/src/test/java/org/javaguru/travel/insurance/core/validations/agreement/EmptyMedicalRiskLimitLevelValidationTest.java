package org.javaguru.travel.insurance.core.validations.agreement;

import lombok.Value;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmptyMedicalRiskLimitLevelValidationTest {

    @Mock private ValidationErrorFactory errorFactory;
    @Mock private AgreementDTO agreement;

    @InjectMocks private EmptyMedicalRiskLimitLevelValidation validation;

    @Test
    void shouldReturnValidationErrorWhenMedicalRiskLimitLevelEnabledAndNullOrBlank() {
        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", true);
        when(agreement.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(agreement.getMedicalRiskLimitLevel()).thenReturn(null);
        ValidationErrorDTO expectedError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_13")).thenReturn(expectedError);

        Optional<ValidationErrorDTO> result = validation.validate(agreement);

        assertTrue(result.isPresent());
        assertEquals(expectedError, result.get());
    }

    @Test
    void shouldNotReturnValidationErrorWhenMedicalRiskLimitLevelEnabledAndIsNotBlank() {
        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", true);
        when(agreement.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(agreement.getMedicalRiskLimitLevel()).thenReturn("LEVEL_10000");
        Optional<ValidationErrorDTO> result = validation.validate(agreement);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotReturnValidationErrorWhenMedicalRiskLimitLevelNotEnabledAndIsBlank() {
        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", false);
        Optional<ValidationErrorDTO> result = validation.validate(agreement);
        assertTrue(result.isEmpty());
    }

}