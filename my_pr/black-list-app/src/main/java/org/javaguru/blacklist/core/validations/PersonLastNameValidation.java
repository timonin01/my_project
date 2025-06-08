package org.javaguru.blacklist.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.blacklist.core.api.dto.BlackListedPersonDTO;
import org.javaguru.blacklist.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PersonLastNameValidation implements BlackListedPersonValidation {

    private final ValidationErrorFactory errorFactory;

    public Optional<ValidationErrorDTO> validate(BlackListedPersonDTO personDTO) {
        return (personDTO.getPersonLastName() == null || personDTO.getPersonLastName().isEmpty())
            ? Optional.of(errorFactory.buildError("ERROR_CODE_2"))
            : Optional.empty();
    }

}
