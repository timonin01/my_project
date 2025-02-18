package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

public interface TravelPremiumUnderwriting {
    public TravelPremiumCalculationResult calculatePremium(TravelCalculatePremiumRequestV1 request);
}
