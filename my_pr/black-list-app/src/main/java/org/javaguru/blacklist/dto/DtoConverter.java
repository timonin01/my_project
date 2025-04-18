package org.javaguru.blacklist.dto;

import org.javaguru.blacklist.core.api.command.BlackListedPersonCoreCommand;
import org.javaguru.blacklist.core.api.command.BlackListedPersonCoreResult;
import org.javaguru.blacklist.core.api.dto.BlackListedPersonDTO;
import org.javaguru.blacklist.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoConverter {

    public BlackListedPersonCoreCommand buildCoreCommand(BlackListedPersonCheckRequest request) {
        BlackListedPersonDTO personDTO = buildPerson(request);
        return new BlackListedPersonCoreCommand(personDTO);
    }

    private BlackListedPersonDTO buildPerson(BlackListedPersonCheckRequest request){
        BlackListedPersonDTO personDTO = new BlackListedPersonDTO();
        personDTO.setPersonFirstName(request.getPersonFirstName());
        personDTO.setPersonLastName(request.getPersonLastName());
        personDTO.setPersonCode(request.getPersonCode());

        return personDTO;
    }

    public BlackListedPersonCheckResponse buildResponse(BlackListedPersonCoreResult coreResult) {
        return coreResult.hasErrors()
            ? buildResponseWithErrors(coreResult.getErrors())
            : buildSuccessfulResponse(coreResult);
    }

    private BlackListedPersonCheckResponse buildResponseWithErrors(List<ValidationErrorDTO> coreErrors) {
        List<ValidationError> errors = transformValidationErrors(coreErrors);
        return new BlackListedPersonCheckResponse(errors);
    }

    private List<ValidationError> transformValidationErrors(List<ValidationErrorDTO> coreErrors) {
        return coreErrors.stream()
            .map(error -> new ValidationError(error.getErrorCode(), error.getDescription()))
            .collect(Collectors.toList());
    }

    private BlackListedPersonCheckResponse buildSuccessfulResponse(BlackListedPersonCoreResult coreResult) {
        BlackListedPersonDTO person = coreResult.getPersonDTO();
        BlackListedPersonCheckResponse response = new BlackListedPersonCheckResponse();
        response.setPersonFirstName(person.getPersonFirstName());
        response.setPersonLastName(person.getPersonLastName());
        response.setPersonCode(person.getPersonCode());
        response.setBlackListed(person.getBlackListed());
        return response;
    }

}
