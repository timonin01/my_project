package org.javaguru.travel.insurance.core.underwriting.calculators.medical.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import org.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class MedicalRiskLimitLevelCalculator {

    private final MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    public BigDecimal calculateMedicalRiskLimitLevel(TravelCalculatePremiumRequestV1 request){
        return medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc(request.getMedicalRiskLimitLevel())
                .map(MedicalRiskLimitLevel::getCoefficient)
                .orElseThrow(() -> new RuntimeException("coefficient not found by MedicalRiskLimit = "
                        + request.getMedicalRiskLimitLevel()));
    }

}
