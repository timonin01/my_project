package org.javaguru.travel.insurance.core.validations.person;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonLastNameFormatValidationTest {

    @Mock private ValidationErrorFactory validationErrorFactory;

    @InjectMocks private PersonLastNameFormatValidation validation;

    @Test
    public void shouldReturnError(){
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonLastName()).thenReturn("Andrey-Best1");
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_23"), anyList())).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldNotReturnError(){
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);
        when(personDTO.getPersonLastName()).thenReturn("Andrey-Best");
        Optional<ValidationErrorDTO> errorDTO = validation.validate(agreementDTO,personDTO);
        assertTrue(errorDTO.isEmpty());
    }

    @Test
    public void shouldNotReturnErrorWhenLastNameNull(){
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);
        when(personDTO.getPersonLastName()).thenReturn(null);
        Optional<ValidationErrorDTO> errorDTO = validation.validate(agreementDTO,personDTO);
        assertTrue(errorDTO.isEmpty());
    }

    @Test
    public void shouldNotReturnErrorWhenLastNameBlank(){
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);
        when(personDTO.getPersonLastName()).thenReturn("");
        Optional<ValidationErrorDTO> errorDTO = validation.validate(agreementDTO,personDTO);
        assertTrue(errorDTO.isEmpty());
    }

}