package org.javaguru.travel.insurance.core.underwriting;

import lombok.Value;
import org.javaguru.travel.insurance.core.api.dto.RiskDTO;

import java.math.BigDecimal;
import java.util.List;

@Value
public class TravelPremiumCalculationResult {

    private BigDecimal totalPremium;

    private List<RiskDTO> riskPremiums;

}
