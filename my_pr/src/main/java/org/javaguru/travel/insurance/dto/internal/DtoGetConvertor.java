package org.javaguru.travel.insurance.dto.internal;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.dto.RiskPremium;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.javaguru.travel.insurance.dto.v2.PersonResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoGetConvertor {

    public TravelGetAgreementCoreCommand buildCoreCommand(String uuid){
        return new TravelGetAgreementCoreCommand(uuid);
    }
    public TravelGetAgreementResponse buildResponse(TravelGetAgreementCoreResult coreResult){
        return (coreResult.hasErrors())
                ? buildResponseWithErrors(coreResult.getErrors())
                : buildSuccessfulResponse(coreResult);
    }

    private TravelGetAgreementResponse buildResponseWithErrors(List<ValidationErrorDTO> errors){
        List<ValidationError> error = transformValidationErrorsToResponse(errors);
        return new TravelGetAgreementResponse(error);
    }

    private List<ValidationError> transformValidationErrorsToResponse(List<ValidationErrorDTO> errors){
        return errors.stream().
                map(error -> new ValidationError(error.getErrorCode(), error.getDescription()))
                .collect(Collectors.toList());
    }

    private TravelGetAgreementResponse buildSuccessfulResponse(TravelGetAgreementCoreResult coreResult){
        TravelGetAgreementResponse response = new TravelGetAgreementResponse();
        AgreementDTO agreement = coreResult.getAgreement();
        loadGetResponseFields(agreement,response);
        loadGetPersons(agreement,response);
        return response;
    }

    private void loadGetResponseFields(AgreementDTO agreement, TravelGetAgreementResponse response){
        response.setUuid(agreement.getUuid());
        response.setCountry(agreement.getCountry());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementPremium(agreement.getAgreementPremium());
    }

    private void loadGetPersons(AgreementDTO agreement, TravelGetAgreementResponse response){
        List<PersonResponseDTO> personResponseDTOS = agreement.getPersons().stream().
                map(this::buildPersonFromResponse).
                toList();
        response.setPersons(personResponseDTOS);
    }

    private PersonResponseDTO buildPersonFromResponse(PersonDTO personDTO){
        PersonResponseDTO person = new PersonResponseDTO();
        person.setPersonFirstName(personDTO.getPersonFirstName());
        person.setPersonLastName(personDTO.getPersonLastName());
        person.setPersonCode(personDTO.getPersonCode());
        person.setPersonBirthDate(personDTO.getPersonBirthDate());
        person.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());

        person.setPersonPremium(personDTO.getRisks().stream()
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        person.setPersonRisks(personDTO.getRisks().stream()
                .map(riskDTO -> new RiskPremium(riskDTO.getRiskIc(), riskDTO.getPremium()))
                .collect(Collectors.toList()));

        return person;
    }

}
