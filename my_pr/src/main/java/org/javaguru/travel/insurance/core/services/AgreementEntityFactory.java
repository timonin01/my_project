package org.javaguru.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import org.javaguru.travel.insurance.core.domain.entities.SelectedRisksEntity;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import org.javaguru.travel.insurance.core.repositories.entities.SelectedRisksEntityRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AgreementEntityFactory {

    private final AgreementEntityRepository agreementEntityRepository;
    private final PersonEntityFactory personEntityFactory;
    private final SelectedRisksEntityRepository selectedRisksEntityRepository;

    AgreementEntity createAgreementEntity(AgreementDTO agreementDTO){
        saveAllPersons(agreementDTO);

        AgreementEntity agreementEntity = new AgreementEntity();
        agreementEntity.setAgreementDateFrom(agreementDTO.getAgreementDateFrom());
        agreementEntity.setAgreementDateTo(agreementDTO.getAgreementDateTo());
        agreementEntity.setCountry(agreementDTO.getCountry());

        BigDecimal premium = agreementDTO.getAgreementPremium();
        if (premium == null) {
            premium = BigDecimal.ZERO;
        }
        agreementEntity.setAgreementPremium(premium);

        agreementDTO.getSelectedRisks().forEach(riskIc -> {
            SelectedRisksEntity riskEntity = new SelectedRisksEntity();
            riskEntity.setAgreement(agreementEntity);
            riskEntity.setRiskIc(riskIc);
            selectedRisksEntityRepository.save(riskEntity);
        });

        return agreementEntity;
    }

    private void saveAllPersons(AgreementDTO agreement) {
        agreement.getPersons().forEach(personDTO -> personEntityFactory.createPersonEntity(personDTO));
    }

}
