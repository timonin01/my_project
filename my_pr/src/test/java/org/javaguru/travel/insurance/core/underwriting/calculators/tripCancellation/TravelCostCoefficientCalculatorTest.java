package org.javaguru.travel.insurance.core.underwriting.calculators.tripCancellation;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.domain.TravelCostCoefficient;
import org.javaguru.travel.insurance.core.repositories.TravelCostCoefficientRepository;
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
class TravelCostCoefficientCalculatorTest {

    @Mock
    private TravelCostCoefficientRepository costCoefficientRepository;

    @InjectMocks
    private TravelCostCoefficientCalculator calculator;

    private PersonDTO person;

    @BeforeEach
    void setUp() {
        person = new PersonDTO();
        person.setTravelCost(BigDecimal.ONE);
    }

    @Test
    void shouldFindCoefficientWhenAgeCoefficientExists() {
        TravelCostCoefficient travelCostCoefficient = mock(TravelCostCoefficient.class);
        when(travelCostCoefficient.getCoefficient()).thenReturn(BigDecimal.TEN);
        when(costCoefficientRepository.findByCoefficient(BigDecimal.ONE)).thenReturn(Optional.of(travelCostCoefficient));

        BigDecimal result = calculator.calculateCostCoefficient(person);

        assertEquals(BigDecimal.TEN, result);
    }

    @Test
    void shouldThrowExceptionWhenTravelCostCoefficientNotFound() {
        when(costCoefficientRepository.findByCoefficient(BigDecimal.ONE)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.calculateCostCoefficient(person));
        assertEquals("Travel Cost coefficient not found for travel cost = " + BigDecimal.ONE, exception.getMessage());
    }

}