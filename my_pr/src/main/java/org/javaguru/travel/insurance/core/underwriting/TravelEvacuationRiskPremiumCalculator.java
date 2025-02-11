package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public class TravelEvacuationRiskPremiumCalculator implements TravelRiskPremiumCalculator{

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return TravelRiskPremiumCalculator.super.calculatePremium(request);
    }

    @Override
    public String getRiskIc() { return "TRAVEL_EVACUATION";}
}
