package org.javaguru.travel.insurance.core.underwriting.calculators.tripCancellation;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.domain.TCAgeCoefficient;
import org.javaguru.travel.insurance.core.repositories.TCAgeCoefficientRepository;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TCAgeCoefficientCalculatorTest {

    @Mock private TCAgeCoefficientRepository ageCoefficientRepository;
    @Mock private DateTimeUtil dateTimeUtil;

    @InjectMocks private TCAgeCoefficientCalculator ageCoefficientCalculator;

    private PersonDTO person;

    @BeforeEach
    void setUp() {
        person = new PersonDTO();
        person.setPersonBirthDate(Date.from(LocalDate.of(2000, 1, 1)
                .atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    @Test
    void shouldFindCoefficientWhenAgeCoefficientExists() {
        int age = 25;
        LocalDate currentDate = LocalDate.of(2025,3,9);
        BigDecimal expectedValue = BigDecimal.valueOf(20);

        when(dateTimeUtil.getCurrentDateTime()).thenReturn(Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        TCAgeCoefficient ageCoefficient = mock(TCAgeCoefficient.class);
        when(ageCoefficient.getCoefficient()).thenReturn(expectedValue);
        when(ageCoefficientRepository.findCoefficient(age)).thenReturn(Optional.of(ageCoefficient));

        BigDecimal coefficient =  ageCoefficientCalculator.calculateAgeCoefficient(person);
        assertEquals(expectedValue,coefficient);
    }

    @Test
    void shouldThrowExceptionWhenTravelCostCoefficientNotFound() {
        int age = 25;
        LocalDate currentDate = LocalDate.of(2025,3,9);
        BigDecimal expectedValue = BigDecimal.valueOf(20);

        when(dateTimeUtil.getCurrentDateTime()).thenReturn(Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        when(ageCoefficientRepository.findCoefficient(age)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ageCoefficientCalculator.calculateAgeCoefficient(person));

        assertEquals("Age coefficient not found for age = " + age, exception.getMessage());
    }


}
