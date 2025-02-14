package org.javaguru.travel.insurance.core.underwriting.calculators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.domain.AgeCoefficient;
import org.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import org.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final CountryDefaultDayRateRepository countryDefaultDayPremium;
    private final DateTimeUtil dateTimeUtil;
    private final AgeCoefficientRepository ageCoefficientRepository;


    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        var dayCount = calculateDayCount(request);
        var dayRate = calculateCountryDefaultDayPremium(request);
        var coefficient = calculateAgeCoefficient(request);
        return dayRate
                .multiply(dayCount)
                .multiply(coefficient)
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

    public BigDecimal calculateAgeCoefficient(TravelCalculatePremiumRequest request){
        var age = calculateAge(request);
        return ageCoefficientRepository.findCoefficient(age)
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(()-> new RuntimeException("Age coefficient not found for age = " + age));
    }

    private Integer calculateAge(TravelCalculatePremiumRequest request) {
        LocalDate personBirthDate = toLocalDate(request.getPersonBirthDate());
        LocalDate currentDate = toLocalDate(dateTimeUtil.getCurrentDateTime());
        return Period.between(personBirthDate, currentDate).getYears();
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @Override
    public String getRiskIc() {return "TRAVEL_MEDICAL";}
}
