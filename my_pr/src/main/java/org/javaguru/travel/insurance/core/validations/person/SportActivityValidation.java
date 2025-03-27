package org.javaguru.travel.insurance.core.validations.person;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.repositories.TypeActivitiesCoefficientRepository;
import org.javaguru.travel.insurance.core.util.Placeholder;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SportActivityValidation extends TravelPersonFieldValidationImpl{

    private final TypeActivitiesCoefficientRepository activitiesCoefficientRepository;
    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        return (isSportActivityNotBlank(person))
            && !existInDatabase(person.getSportActivity())
            ? Optional.of(buildValidationError(person.getSportActivity()))
            : Optional.empty();
    }

    private ValidationErrorDTO buildValidationError(String sportActivity) {
        Placeholder placeholder = new Placeholder("NOT_SUPPORTED_SPORT_ACTIVITY", sportActivity);
        return errorFactory.buildError("ERROR_CODE_26", List.of(placeholder));
    }

    private boolean isSportActivityNotBlank(PersonDTO person) {
        return person.getSportActivity() != null && !person.getSportActivity().isBlank();
    }

    private boolean existInDatabase(String sportActivity) {
        return activitiesCoefficientRepository
            .findCoefficient(sportActivity).isPresent();
    }

}
