package  org.javaguru.travel.insurance.core.underwriting.calculators.sportActivities;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.domain.TypeActivitiesCoefficient;
import org.javaguru.travel.insurance.core.repositories.TypeActivitiesCoefficientRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TypeActivitiesCoefficientCalculator {

    private final TypeActivitiesCoefficientRepository activitiesCoefficientRepository;

    public BigDecimal calculateCoefficient(PersonDTO person){
        return activitiesCoefficientRepository.findCoefficient(person.getSportActivity()).
            map(TypeActivitiesCoefficient::getCoefficient)
            .orElseThrow(() -> new RuntimeException("Coefficient not found for sportActivity = " + person.getSportActivity()));
    }

    private static BigDecimal getDefaultValue() {
        return BigDecimal.ONE;
    }

}
