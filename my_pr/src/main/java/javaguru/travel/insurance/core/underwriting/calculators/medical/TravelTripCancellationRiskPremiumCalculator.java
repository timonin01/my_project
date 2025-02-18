package javaguru.travel.insurance.core.underwriting.calculators.medical;

import javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.math.BigDecimal;

public class TravelTripCancellationRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request) {
        return TravelRiskPremiumCalculator.super.calculatePremium(request);
    }

    @Override
    public String getRiskIc() {return "TRAVEL_CANCELLATION";}
}
