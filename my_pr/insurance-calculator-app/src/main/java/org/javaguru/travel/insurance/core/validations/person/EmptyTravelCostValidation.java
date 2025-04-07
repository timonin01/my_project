package  org.javaguru.travel.insurance.core.validations.person;

import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.javaguru.travel.insurance.core.validations.person.TravelPersonFieldValidationImpl;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmptyTravelCostValidation extends TravelPersonFieldValidationImpl {

    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        return (containsTravelCancellation(agreement)
                && person.getTravelCost() == null)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_19"))
                : Optional.empty();
    }

    private boolean containsTravelCancellation(AgreementDTO agreement) {
        return agreement.getSelectedRisks() != null
                && agreement.getSelectedRisks().contains("TRAVEL_CANCELLATION");
    }
}
