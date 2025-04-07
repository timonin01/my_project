package  org.javaguru.travel.insurance.core.underwriting.calculators.sportActivities;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelSportActivitiesRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final TypeActivitiesCoefficientCalculator typeActivitiesCoefficientCalculator;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        var coefficientCalculate = typeActivitiesCoefficientCalculator.calculateCoefficient(person);
        return coefficientCalculate.
            setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {return "TRAVEL_SPORT_ACTIVITIES";}
}
