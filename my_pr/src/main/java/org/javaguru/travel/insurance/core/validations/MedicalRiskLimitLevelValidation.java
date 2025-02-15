package org.javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class MedicalRiskLimitLevelValidation extends TravelRequestValidationImpl{

    @Value( "${medical.risk.limit.level.enabled:false}" )
    private Boolean medicalRiskLimitLevelEnabled;

    private final ValidationErrorFactory validationErrorFactory;
    private final ClassifierValueRepository classifierValueRepository;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return ( medicalRiskLimitLevelEnabled &&
                containsTravelMedical(request)&&
                isMedicalRiskLimitLevelIsNullOrBlank(request))&&
                risksContainsMedicalRiskLimitLevel(request)
                ?Optional.of(validationErrorFactory.buildError("ERROR_CODE_14"))
                :Optional.empty();
    }

    private boolean containsTravelMedical(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks() != null
                && request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }

    private boolean isMedicalRiskLimitLevelIsNullOrBlank(TravelCalculatePremiumRequest request) {
        return request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isBlank();
    }

    private boolean risksContainsMedicalRiskLimitLevel(TravelCalculatePremiumRequest request){
        return classifierValueRepository
                .findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", request.getMedicalRiskLimitLevel()).isPresent();
    }

}
