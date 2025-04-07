package  org.javaguru.travel.insurance.core.underwriting.calculators.tripCancellation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.domain.TCTravelCostCoefficient;
import org.javaguru.travel.insurance.core.repositories.TCTravelCostCoefficientRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelCostCoefficientCalculator {

    private final TCTravelCostCoefficientRepository costCoefficientRepository;

    public BigDecimal calculateCostCoefficient(PersonDTO personDTO){
        return costCoefficientRepository.findByCoefficient(personDTO.getTravelCost()).
                map(TCTravelCostCoefficient::getCoefficient).
                orElseThrow(() -> new RuntimeException("Travel Cost coefficient not found for travel cost = " + personDTO.getTravelCost()));
    }

}
