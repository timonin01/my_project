package  org.javaguru.travel.insurance.core.underwriting.calculators;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;

import java.math.BigDecimal;

public class TravelLoseBaggageRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return TravelRiskPremiumCalculator.super.calculatePremium(agreement,person);
    }

    @Override
    public String getRiskIc() {return "TRAVEL_LOSS_BAGGAGE";}
}
