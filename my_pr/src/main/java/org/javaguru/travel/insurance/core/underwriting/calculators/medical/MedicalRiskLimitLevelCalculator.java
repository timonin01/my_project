package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import org.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class MedicalRiskLimitLevelCalculator {

    private final MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    public BigDecimal calculateMedicalRiskLimitLevel(AgreementDTO agreement){
        return medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc(agreement.getMedicalRiskLimitLevel())
                .map(MedicalRiskLimitLevel::getCoefficient)
                .orElseThrow(() -> new RuntimeException("coefficient not found by MedicalRiskLimit = "
                        + agreement.getMedicalRiskLimitLevel()));
    }

}
