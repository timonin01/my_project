package org.javaguru.travel.insurance.core.underwriting.calculators.sportActivities;

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
class TravelSportActivitiesRiskPremiumCalculatorTest {

    @Mock private TypeActivitiesCoefficientCalculator activitiesCoefficientCalculator;

    @InjectMocks
    private TravelSportActivitiesRiskPremiumCalculator calculator;

    AgreementDTO agreementDTO;
    PersonDTO personDTO;

    @BeforeEach
    public void setUp(){
        agreementDTO = new AgreementDTO();
        personDTO = new PersonDTO();
    }

    @Test
    public void shouldCalculatePremium(){
        BigDecimal sportActivityCoefficient = BigDecimal.valueOf(3.5);

        personDTO.setSportActivity("DIVING");
        when(activitiesCoefficientCalculator.calculateCoefficient(personDTO)).thenReturn(sportActivityCoefficient);

        BigDecimal exceptedResult = calculator.calculatePremium(agreementDTO,personDTO);

        assertEquals(sportActivityCoefficient.setScale(2, RoundingMode.HALF_UP),exceptedResult);
    }

}
