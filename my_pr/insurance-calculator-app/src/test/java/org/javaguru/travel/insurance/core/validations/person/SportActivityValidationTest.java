package org.javaguru.travel.insurance.core.validations.person;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.domain.TypeActivitiesCoefficient;
import org.javaguru.travel.insurance.core.repositories.TypeActivitiesCoefficientRepository;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SportActivityValidationTest {

    @Mock private TypeActivitiesCoefficientRepository activitiesCoefficientRepository;
    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks private SportActivityValidation validation;

    AgreementDTO agreementDTO = new AgreementDTO();
    PersonDTO personDTO;

    @BeforeEach
    public void setUp(){
        personDTO = mock(PersonDTO.class);
    }

    @Test
    public void shouldNotReturnErrorWhenFieldIsCorrect(){
        when(personDTO.getSportActivity()).thenReturn("DIVING");
        TypeActivitiesCoefficient typeActivitiesCoefficient = mock(TypeActivitiesCoefficient.class);
        when(activitiesCoefficientRepository.findCoefficient("DIVING")).thenReturn(Optional.of(typeActivitiesCoefficient));
        Optional<ValidationErrorDTO> result = validation.validate(agreementDTO,personDTO);
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldNotReturnErrorWhenFieldIsNull(){
        when(personDTO.getSportActivity()).thenReturn(null);
        Optional<ValidationErrorDTO> result = validation.validate(agreementDTO,personDTO);
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldNotReturnErrorWhenFieldIsBlank(){
        when(personDTO.getSportActivity()).thenReturn("");
        Optional<ValidationErrorDTO> result = validation.validate(agreementDTO,personDTO);
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnError(){
        when(personDTO.getSportActivity()).thenReturn("DIVINGGGG");
        TypeActivitiesCoefficient typeActivitiesCoefficient = mock(TypeActivitiesCoefficient.class);
        when(activitiesCoefficientRepository.findCoefficient("DIVINGGGG")).thenReturn(Optional.empty());
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError(eq("ERROR_CODE_26"), any())).thenReturn(validationError);
        Optional<ValidationErrorDTO> validationErrorOpt = validation.validate(agreementDTO, personDTO);
        assertTrue(validationErrorOpt.isPresent());
        assertSame(validationError, validationErrorOpt.get());
    }

}
