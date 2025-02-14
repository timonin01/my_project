package org.javaguru.travel.insurance.core.underwriting.calculators;

import org.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelMedicalRiskPremiumCalculatorTest {

    @Mock private CountryDefaultDayRateRepository countryDefaultDayRateRepository;
    @Mock private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private TravelMedicalRiskPremiumCalculator calculator;

    @Test
    public void shouldReturnCorrectResponse(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn("SPAIN");
        when(dateTimeUtil.calculateDaysBetween(any(), any())).thenReturn(2L);
        CountryDefaultDayRate countryDefaultDayRate = mock(CountryDefaultDayRate.class);
        when(countryDefaultDayRate.getDefaultDayRate()).thenReturn(BigDecimal.TWO);
        when(countryDefaultDayRateRepository.findByCountryIc("SPAIN")).thenReturn(Optional.of(countryDefaultDayRate));
        BigDecimal premium = calculator.calculatePremium(request);
        assertEquals(premium.stripTrailingZeros(),
                new BigDecimal("4").stripTrailingZeros());
    }

}