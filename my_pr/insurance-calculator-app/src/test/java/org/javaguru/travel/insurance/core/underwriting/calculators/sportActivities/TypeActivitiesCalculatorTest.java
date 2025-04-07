package org.javaguru.travel.insurance.core.underwriting.calculators.sportActivities;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.domain.TypeActivitiesCoefficient;
import org.javaguru.travel.insurance.core.repositories.TypeActivitiesCoefficientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TypeActivitiesCalculatorTest {

    @Mock private TypeActivitiesCoefficientRepository repository;

    @InjectMocks private TypeActivitiesCoefficientCalculator calculator;

    PersonDTO personDTO;

    @BeforeEach
    public void setUp(){
        personDTO = new PersonDTO();
    }

    @Test
    public void shouldReturnCoefficient(){
        personDTO.setSportActivity("DIVING");
        BigDecimal result = BigDecimal.valueOf(3.5);

        TypeActivitiesCoefficient activitiesCoefficient = mock(TypeActivitiesCoefficient.class);
        when(activitiesCoefficient.getCoefficient()).thenReturn(result);
        when(repository.findCoefficient(personDTO.getSportActivity())).thenReturn(Optional.of(activitiesCoefficient));

        BigDecimal exceptedResult = calculator.calculateCoefficient(personDTO);

        assertEquals(result,exceptedResult);

    }

    @Test
    public void shouldNotReturnCoefficient(){
        personDTO.setSportActivity("DIVING");
        BigDecimal result = BigDecimal.valueOf(3.5);

        when(repository.findCoefficient(personDTO.getSportActivity())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.calculateCoefficient(personDTO));

        assertEquals("Coefficient not found for sportActivity = " + personDTO.getSportActivity(), exception.getMessage());
    }

}
