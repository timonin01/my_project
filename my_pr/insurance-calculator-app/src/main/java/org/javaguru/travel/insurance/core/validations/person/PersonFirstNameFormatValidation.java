package  org.javaguru.travel.insurance.core.validations.person;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.util.Placeholder;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.javaguru.travel.insurance.core.validations.person.TravelPersonFieldValidationImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PersonFirstNameFormatValidation extends TravelPersonFieldValidationImpl {

    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        return (!isPersonFirstNameNullOrBlank(person) && !isValidFormat(person))
                ? Optional.of(buildValidationError(person))
                :Optional.empty();
    }

    private ValidationErrorDTO buildValidationError(PersonDTO person) {
        Placeholder placeholder = new Placeholder("PERSON_FIRST_NAME", person.getPersonFirstName());
        return errorFactory.buildError("ERROR_CODE_22", List.of(placeholder));
    }

    private boolean isPersonFirstNameNullOrBlank(PersonDTO person) {
        return person.getPersonFirstName() == null || person.getPersonFirstName().isBlank();
    }

    private boolean isValidFormat(PersonDTO person) {
        String regex = "^[a-zA-Z\\s-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(person.getPersonFirstName());
        return matcher.matches();
    }
}
