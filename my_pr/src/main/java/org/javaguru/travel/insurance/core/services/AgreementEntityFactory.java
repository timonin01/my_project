package org.javaguru.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.domain.AgreementEntity;
import org.javaguru.travel.insurance.core.repositories.AgreementEntityRepository;
import org.javaguru.travel.insurance.core.repositories.PersonEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AgreementEntityFactory {

    private final AgreementEntityRepository agreementEntityRepository;
    private final PersonEntityFactory personEntityFactory;

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
        return agreementEntityRepository.save(agreementEntity);
    }

    private void saveAllPersons(AgreementDTO agreement) {
        agreement.getPersons().forEach(personDTO -> personEntityFactory.createPersonEntity(personDTO));
    }

}
