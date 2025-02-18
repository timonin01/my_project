package javaguru.travel.insurance.core.underwriting;

import lombok.Value;
import javaguru.travel.insurance.dto.RiskPremium;

import java.math.BigDecimal;
import java.util.List;

@Value
public class TravelPremiumCalculationResult {

    private BigDecimal totalPremium;

    private List<RiskPremium> riskPremiums;

}
