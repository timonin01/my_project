package  org.javaguru.travel.insurance.core.validations.person;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.javaguru.travel.insurance.core.validations.person.TravelPersonFieldValidationImpl;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class EmptySportActivityValidation extends TravelPersonFieldValidationImpl {

    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        return (containsTravelSportActivities(agreement)
            && (person.getSportActivity() == null || person.getSportActivity().isBlank()))
            ? Optional.of(errorFactory.buildError("ERROR_CODE_25"))
            : Optional.empty();
    }

    private boolean containsTravelSportActivities(AgreementDTO agreement) {
        return agreement.getSelectedRisks() != null
            && agreement.getSelectedRisks().contains("TRAVEL_SPORT_ACTIVITIES");
    }

}
