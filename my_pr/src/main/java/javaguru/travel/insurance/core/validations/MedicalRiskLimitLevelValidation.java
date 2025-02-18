package javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class MedicalRiskLimitLevelValidation extends TravelRequestValidationImpl{

    @Value( "${medical.risk.limit.level.enabled:false}" )
    private Boolean medicalRiskLimitLevelEnabled;

    private final ClassifierValueRepository classifierValueRepository;
    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (isMedicalRiskLimitLevelNotBlank(request))
                && !existInDatabase(request.getMedicalRiskLimitLevel())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_14"))
                : Optional.empty();
    }

    private boolean isMedicalRiskLimitLevelNotBlank(TravelCalculatePremiumRequestV1 request) {
        return request.getMedicalRiskLimitLevel() != null && !request.getMedicalRiskLimitLevel().isBlank();
    }

    private boolean existInDatabase(String medicalRiscLimitLevelIc) {
        return classifierValueRepository
                .findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", medicalRiscLimitLevelIc).isPresent();
    }


}
