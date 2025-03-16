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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonCodeFormatValidationTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private PersonCodeFormatValidation personCodeFormatValidation;

    @Test
    public void shouldReturnError(){
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonCode()).thenReturn("123456-123456");
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_21"), anyList())).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = personCodeFormatValidation.validate(agreement, person);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldNotReturnError(){
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);
        when(personDTO.getPersonCode()).thenReturn("123456-12345");
        Optional<ValidationErrorDTO> errorDTO = personCodeFormatValidation.validate(agreementDTO,personDTO);
        assertTrue(errorDTO.isEmpty());
    }

    @Test
    public void shouldNotReturnErrorWherePersonCodeIsNull(){
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);
        when(personDTO.getPersonCode()).thenReturn(null);
        Optional<ValidationErrorDTO> errorDTO = personCodeFormatValidation.validate(agreementDTO,personDTO);
        assertTrue(errorDTO.isEmpty());
    }

    @Test
    public void shouldNotReturnErrorWherePersonCodeIsBlunk(){
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);
        when(personDTO.getPersonCode()).thenReturn("");
        Optional<ValidationErrorDTO> errorDTO = personCodeFormatValidation.validate(agreementDTO,personDTO);
        assertTrue(errorDTO.isEmpty());
    }

}