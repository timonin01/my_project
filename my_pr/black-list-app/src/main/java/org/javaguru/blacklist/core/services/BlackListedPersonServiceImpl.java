package org.javaguru.blacklist.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.blacklist.core.api.command.BlackListedPersonCoreCommand;
import org.javaguru.blacklist.core.api.command.BlackListedPersonCoreResult;
import org.javaguru.blacklist.core.api.dto.BlackListedPersonDTO;
import org.javaguru.blacklist.core.api.dto.ValidationErrorDTO;
import org.javaguru.blacklist.core.repositories.BlackListedPersonRepository;
import org.javaguru.blacklist.core.validations.BlackListedPersonValidation;
import org.javaguru.blacklist.core.validations.BlackListedPersonValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class BlackListedPersonServiceImpl implements BlackListedPersonService{

    private final BlackListedPersonValidator validator;
    private final BlackListedPersonRepository repository;

    @Override
    public BlackListedPersonCoreResult check(BlackListedPersonCoreCommand command) {
        List<ValidationErrorDTO> errors = validator.validate(command.getPersonDTO());
        if (errors.isEmpty()) {
            BlackListedPersonDTO personDTO = command.getPersonDTO();
            boolean isBlacklisted = repository.findBy(
                personDTO.getPersonFirstName(),
                personDTO.getPersonLastName(),
                personDTO.getPersonCode()
            ).isPresent();
            personDTO.setBlackListed(isBlacklisted);
            return new BlackListedPersonCoreResult(personDTO);
        } else {
            return new BlackListedPersonCoreResult(errors);
        }
    }
}
