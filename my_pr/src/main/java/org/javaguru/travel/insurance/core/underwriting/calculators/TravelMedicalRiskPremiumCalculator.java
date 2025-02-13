package org.javaguru.travel.insurance.core.underwriting.calculators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import org.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final CountryDefaultDayRateRepository countryDefaultDayPremium;
    private final DateTimeUtil dateTimeUtil;


    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        var dayCount = calculateDayCount(request);
        var dayRate = calculateCountryDefaultDayPremium(request);
        return dayRate.multiply(dayCount)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateDayCount(TravelCalculatePremiumRequest request){
        var dayCount = dateTimeUtil.calculateDaysBetween(request.getAgreementDateFrom(),
            request.getAgreementDateTo());
        return new BigDecimal(dayCount);
    }

    public BigDecimal calculateCountryDefaultDayPremium(TravelCalculatePremiumRequest request){
        return countryDefaultDayPremium.findByCountryIc(request.getCountry())
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country day rate not found by country id = " + request.getCountry()));
    }

    @Override
    public String getRiskIc() {return "TRAVEL_MEDICAL";}
}
