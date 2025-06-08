package org.javaguru.blacklist.core.validations;

import org.javaguru.blacklist.core.api.dto.BlackListedPersonDTO;
import org.javaguru.blacklist.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;

public interface BlackListedPersonValidation {

    Optional<ValidationErrorDTO> validate(BlackListedPersonDTO personDTO);

}
