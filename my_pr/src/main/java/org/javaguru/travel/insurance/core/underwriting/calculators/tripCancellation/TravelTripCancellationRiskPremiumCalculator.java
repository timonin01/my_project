package org.javaguru.travel.insurance.core.underwriting.calculators.tripCancellation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.repositories.TravelCostCoefficientRepository;
import org.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelTripCancellationRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final TravelCostCoefficientCalculator costCoefficientCalculator;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        var costCoefficient = costCoefficientCalculator.calculateCostCoefficient(person);
        return costCoefficient.
                setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {return "TRAVEL_CANCELLATION";}
}
