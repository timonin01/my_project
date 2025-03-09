package org.javaguru.travel.insurance.core.underwriting.calculators.tripCancellation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.domain.TCCountrySafetyRatingCoefficient;
import org.javaguru.travel.insurance.core.repositories.TCCountrySafetyRatingCoefficientRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TCCountrySafetyRatingCoefficientCalculator {

    private final TCCountrySafetyRatingCoefficientRepository countrySafetyRatingCoefficientRepository;

    public BigDecimal calculateCountryCoefficient(AgreementDTO agreementDTO){
        return countrySafetyRatingCoefficientRepository.findByCountryIc(agreementDTO.getCountry())
                .map(TCCountrySafetyRatingCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Country safety rating coefficient not found by country id = " + agreementDTO.getCountry()));
    }

}
