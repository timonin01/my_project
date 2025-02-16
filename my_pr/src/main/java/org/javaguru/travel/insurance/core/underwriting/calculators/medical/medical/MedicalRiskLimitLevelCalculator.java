package org.javaguru.travel.insurance.core.underwriting.calculators.medical.medical;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import org.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class MedicalRiskLimitLevelCalculator {

    private final MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    public BigDecimal calculateMedicalRiskLimitLevel(TravelCalculatePremiumRequest request){
        return medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc(request.getMedicalRiskLimitLevel())
                .map(MedicalRiskLimitLevel::getCoefficient)
                .orElseThrow(() -> new RuntimeException("coefficient not found by MedicalRiskLimit = "
                        + request.getMedicalRiskLimitLevel()));
    }

}
