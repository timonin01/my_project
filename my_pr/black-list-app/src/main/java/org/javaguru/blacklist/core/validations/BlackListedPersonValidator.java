package org.javaguru.blacklist.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.blacklist.core.api.dto.BlackListedPersonDTO;
import org.javaguru.blacklist.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class BlackListedPersonValidator{

    private final List<BlackListedPersonValidation> validations;

    public List<ValidationErrorDTO> validate(BlackListedPersonDTO personDTO) {
        return validations.stream()
            .map(v -> v.validate(personDTO))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
    }

}
