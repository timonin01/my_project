package org.javaguru.travel.insurance.core.validations.person;

import jakarta.validation.constraints.AssertTrue;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
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
class EmptySportActivityValidationTest {

    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks private EmptySportActivityValidation validation;

    PersonDTO personDTO;
    AgreementDTO agreementDTO;

    @BeforeEach
    public void setUp(){
        agreementDTO = mock(AgreementDTO.class);
        when(agreementDTO.getSelectedRisks()).thenReturn(List.of("TRAVEL_SPORT_ACTIVITIES"));
        personDTO = mock(PersonDTO.class);
    }


    @Test
    public void shouldNotReturnError(){
        when(personDTO.getSportActivity()).thenReturn("DIVING");
        Optional<ValidationErrorDTO> result = validation.validate(agreementDTO,personDTO);
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenSportActivityIsBlank(){
        when(personDTO.getSportActivity()).thenReturn("");
        ValidationErrorDTO expectedError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_25")).thenReturn(expectedError);

        Optional<ValidationErrorDTO> result = validation.validate(agreementDTO,personDTO);

        assertTrue(result.isPresent());
        assertEquals(expectedError, result.get());
    }

    @Test
    public void shouldReturnErrorWhenSportActivityIsNull(){
        when(personDTO.getSportActivity()).thenReturn(null);
        ValidationErrorDTO expectedError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_25")).thenReturn(expectedError);

        Optional<ValidationErrorDTO> result = validation.validate(agreementDTO,personDTO);

        assertTrue(result.isPresent());
        assertEquals(expectedError, result.get());
    }


}
