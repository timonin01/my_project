package org.javaguru.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelAgreementValidator agreementValidator;
    private final RiskPremiumsForAllPersonsCalculator riskPremiumsForAllPersonsCalculator;
    private final TotalAgreementPremiumCalculator totalAgreementPremiumCalculator;
    private final AgreementEntityFactory agreementEntityFactory;

    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command) {
        List<ValidationErrorDTO> errors = agreementValidator.validate(command.getAgreement());
        if(errors.isEmpty()) {
            agreementEntityFactory.createAgreementEntity(command.getAgreement());
            return buildResponse(command.getAgreement());
        }
        else{
            return buildResponse(errors);
        }
    }

    private TravelCalculatePremiumCoreResult buildResponse(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }

    private TravelCalculatePremiumCoreResult buildResponse(AgreementDTO agreement) {
        riskPremiumsForAllPersonsCalculator.calculateRiskPremiumsForAllPersons(agreement);

        BigDecimal totalAgreementPremium = totalAgreementPremiumCalculator
                .calculateTotalAgreementPremium(agreement);

        agreement.setAgreementPremium(totalAgreementPremium);
        TravelCalculatePremiumCoreResult coreResult = new TravelCalculatePremiumCoreResult();
        coreResult.setAgreement(agreement);
        return coreResult;
    }
}
