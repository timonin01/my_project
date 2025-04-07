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
public class PersonLastNameFormatValidation extends TravelPersonFieldValidationImpl {

    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        return (!isLastNameNullOrBlank(person) & !validateString(person))
                ?Optional.of(buildValidationError(person))
                :Optional.empty();
    }

    private boolean isLastNameNullOrBlank(PersonDTO person) {
        return person.getPersonLastName() == null || person.getPersonLastName().isBlank();
    }

    private ValidationErrorDTO buildValidationError(PersonDTO person) {
        Placeholder placeholder = new Placeholder("PERSON_LAST_NAME", person.getPersonLastName());
        return validationErrorFactory.buildError("ERROR_CODE_23", List.of(placeholder));
    }

    private static boolean validateString(PersonDTO person) {
        String regex = "^[a-zA-Z\\s-]+$"; // Английские буквы, пробел и тире

        if(person.getPersonLastName() == null){return false;}
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(person.getPersonLastName());
        return  matcher.matches();
    }
}
