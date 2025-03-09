package org.javaguru.travel.insurance.core.underwriting.calculators.tripCancellation;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCancellationRiskPremiumCalculatorTest {

    @Mock private TravelCostCoefficientCalculator costCoefficientCalculator;
    @Mock private TCAgeCoefficientCalculator ageCoefficientCalculator;
    @Mock private TCCountrySafetyRatingCoefficientCalculator countrySafetyRatingCoefficientCalculator;

    @InjectMocks TravelCancellationRiskPremiumCalculator calculator;

    private AgreementDTO agreement;
    private PersonDTO person;

    @BeforeEach
    void setUp() {
        agreement = new AgreementDTO();
        person = new PersonDTO();
    }

    @Test
    void shouldCalculatePremiumCorrectly() {
        BigDecimal costCoefficient = BigDecimal.TEN;
        BigDecimal ageCoefficient = BigDecimal.valueOf(20);
        BigDecimal countryCoefficient = BigDecimal.valueOf(5);

        when(costCoefficientCalculator.calculateCostCoefficient(person)).thenReturn(costCoefficient);
        when(ageCoefficientCalculator.calculateAgeCoefficient(person)).thenReturn(ageCoefficient);
        when(countrySafetyRatingCoefficientCalculator.calculateCountryCoefficient(agreement)).thenReturn(countryCoefficient);

        BigDecimal exceptedValue = costCoefficient
                .multiply(ageCoefficient)
                .multiply(countryCoefficient)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal result = calculator.calculatePremium(agreement,person);

        assertEquals(exceptedValue,result);
    }

}