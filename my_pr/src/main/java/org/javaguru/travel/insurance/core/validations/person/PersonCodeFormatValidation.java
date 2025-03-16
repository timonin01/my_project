package org.javaguru.travel.insurance.core.validations.person;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.util.Placeholder;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PersonCodeFormatValidation extends TravelPersonFieldValidationImpl{

    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        return(!isPersonCodeNullOrBlank(person) & !validateString(person))
                ?Optional.of(buildValidationError(person))
                :Optional.empty();
    }

    private boolean isPersonCodeNullOrBlank(PersonDTO person) {
        return person.getPersonCode() == null || person.getPersonCode().isBlank();
    }

    private ValidationErrorDTO buildValidationError(PersonDTO person) {
        Placeholder placeholder = new Placeholder("PERSONAL_CODE", person.getPersonCode());
        return validationErrorFactory.buildError("ERROR_CODE_21", List.of(placeholder));
    }

    private static boolean validateString(PersonDTO person) {
        // Регулярное выражение для проверки формата XXXXXX-XXXXX, где X - цифра
        String regex = "^\\d{6}-\\d{5}$";

        // Создаем Pattern и Matcher
        if(person.getPersonCode() == null){return false;}
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(person.getPersonCode());

        // Проверяем соответствие строки шаблону
        return matcher.matches();
    }
}
