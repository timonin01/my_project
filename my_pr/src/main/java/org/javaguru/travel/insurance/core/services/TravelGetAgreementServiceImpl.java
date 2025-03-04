package org.javaguru.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.TravelAgreementUuidValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelGetAgreementServiceImpl implements   TravelGetAgreementService{

    private final TravelAgreementUuidValidator validator;
    private final AgreementDTOLoader loader;

    @Override
    public TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command) {
        List<ValidationErrorDTO> error = validator.validate(command.getUuid());
        return (error.isEmpty())
                ?buildResponse(command.getUuid())
                :buildResponse(error);
    }

    private TravelGetAgreementCoreResult buildResponse(List<ValidationErrorDTO> errorDTOS){
        return new TravelGetAgreementCoreResult(errorDTOS);
    }

    private TravelGetAgreementCoreResult buildResponse(String uuid){
        AgreementDTO agreementDTO = loader.load(uuid);
        TravelGetAgreementCoreResult coreResult = new TravelGetAgreementCoreResult();
        coreResult.setAgreement(agreementDTO);
        return coreResult;
    }
}
