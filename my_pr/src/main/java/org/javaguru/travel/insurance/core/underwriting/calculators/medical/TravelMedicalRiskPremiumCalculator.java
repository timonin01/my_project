package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final TMCountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    private final DayCountCalculator dayCountCalculator;
    private final TMAgeCoefficientCalculator ageCoefficientCalculator;
    private final MedicalRiskLimitLevelCalculator medicalRiskLimitLevelCalculator;


    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        var dayCount = dayCountCalculator.calculateDayCount(agreement);
        var dayRate = countryDefaultDayRateCalculator.calculateCountryDefaultDayPremium(agreement);
        var ageCoefficient = ageCoefficientCalculator.calculateAgeCoefficient(person);
        var coefficient = medicalRiskLimitLevelCalculator.calculateMedicalRiskLimitLevel(person);
        return dayRate
                .multiply(dayCount)
                .multiply(ageCoefficient)
                .multiply(coefficient)
                .setScale(2, RoundingMode.HALF_UP);
    }


    @Override
    public String getRiskIc() {return "TRAVEL_MEDICAL";}
}
