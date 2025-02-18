package org.javaguru.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.RiskPremium;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class SelectedRisksPremiumCalculator {

    private final List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    public List<RiskPremium> calculatePremiumForAllRisks(TravelCalculatePremiumRequestV1 request) {
        List<RiskPremium> riskPremiums = request.getSelectedRisks().stream()
                .map(riskIc -> {
                    BigDecimal riskPremium = calculatePremiumForRisk(riskIc, request);
                    return new RiskPremium(riskIc, riskPremium);
                })
                .toList();
        return riskPremiums;
    }

    private BigDecimal calculatePremiumForRisk(String riskIc, TravelCalculatePremiumRequestV1 request) {
        return findRiskPremiumCalculator(riskIc).calculatePremium(request);
    }

    private TravelRiskPremiumCalculator findRiskPremiumCalculator(String riskIc) {
        return riskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }

}
