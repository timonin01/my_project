package org.javaguru.travel.insurance.core.underwriting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.javaguru.travel.insurance.core.util.RiskPremium;

import java.math.BigDecimal;
import java.util.List;

@Value
public class TravelPremiumCalculationResult {

    private BigDecimal totalPremium;

    private List<RiskPremium> riskPremiums;

}
