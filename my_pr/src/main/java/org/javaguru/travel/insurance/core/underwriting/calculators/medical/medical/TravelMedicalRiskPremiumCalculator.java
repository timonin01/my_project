package org.javaguru.travel.insurance.core.underwriting.calculators.medical.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    private final DayCountCalculator dayCountCalculator;
    private final AgeCoefficientCalculator ageCoefficientCalculator;


    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        var dayCount = dayCountCalculator.calculateDayCount(request);
        var dayRate = countryDefaultDayRateCalculator.calculateCountryDefaultDayPremium(request);
        var coefficient = ageCoefficientCalculator.calculateAgeCoefficient(request);
        return dayRate
                .multiply(dayCount)
                .multiply(coefficient)
                .setScale(2, RoundingMode.HALF_UP);
    }


    @Override
    public String getRiskIc() {return "TRAVEL_MEDICAL";}
}
