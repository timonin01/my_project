package org.javaguru.blacklist.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.blacklist.core.api.dto.BlackListedPersonDTO;
import org.javaguru.blacklist.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PersonCodeValidation implements BlackListedPersonValidation {

    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(BlackListedPersonDTO personDTO) {
        return (personDTO.getPersonCode() == null || personDTO.getPersonCode().isEmpty())
            ? Optional.of(errorFactory.buildError("ERROR_CODE_3"))
            : Optional.empty();
    }

}
