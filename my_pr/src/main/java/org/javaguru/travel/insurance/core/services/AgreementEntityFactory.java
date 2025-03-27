package org.javaguru.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.domain.entities.*;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementPersonEntityRepository;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementPersonRisksEntityRepository;
import org.javaguru.travel.insurance.core.repositories.entities.SelectedRisksEntityRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AgreementEntityFactory {

    private final AgreementEntityRepository agreementEntityRepository;
    private final PersonEntityFactory personEntityFactory;
    private final SelectedRisksEntityRepository selectedRisksEntityRepository;
    private final AgreementPersonEntityRepository agreementPersonEntityRepository;
    private final AgreementPersonRisksEntityRepository agreementPersonRisksEntityRepository;

    AgreementEntity createAgreementEntity(AgreementDTO agreementDTO){
        saveAllPersons(agreementDTO);

        AgreementEntity agreementEntity = saveAgreement(agreementDTO);

        saveSelectedRisks(agreementDTO,agreementEntity);

        saveAgreementPersons(agreementDTO,agreementEntity);

        saveAgreementPersonRisks(agreementDTO,agreementEntity);

        return agreementEntity;
    }

    private void saveAllPersons(AgreementDTO agreement) {
        agreement.getPersons().forEach(personDTO -> personEntityFactory.createPersonEntity(personDTO));
    }

    private AgreementEntity saveAgreement(AgreementDTO agreementDTO){
        AgreementEntity agreementEntity = new AgreementEntity();
        agreementEntity.setAgreementDateFrom(agreementDTO.getAgreementDateFrom());
        agreementEntity.setAgreementDateTo(agreementDTO.getAgreementDateTo());
        agreementEntity.setCountry(agreementDTO.getCountry());
        agreementEntity.setUuid(UUID.randomUUID().toString());

        BigDecimal premium = agreementDTO.getAgreementPremium();
        if (premium == null) {
            premium = BigDecimal.ZERO;
        }
        agreementEntity.setAgreementPremium(premium);

        return agreementEntity;
    }

    private void saveSelectedRisks(AgreementDTO agreementDTO,AgreementEntity agreementEntity){
        agreementDTO.getSelectedRisks().forEach(riskIc -> {
            SelectedRisksEntity riskEntity = new SelectedRisksEntity();
            riskEntity.setAgreement(agreementEntity);
            riskEntity.setRiskIc(riskIc);
            selectedRisksEntityRepository.save(riskEntity);
        });
    }

    private void saveAgreementPersons(AgreementDTO agreementDTO,AgreementEntity agreementEntity){
        agreementDTO.getPersons().forEach(personDTO -> {
            agreementPersonEntityRepository.save(saveAgreementPerson(personDTO,agreementEntity));
        });
    }

    private void saveAgreementPersonRisks(AgreementDTO agreementDTO, AgreementEntity agreementEntity) {
        agreementDTO.getPersons().forEach(personDTO -> {
            if (personDTO.getRisks() != null) {
                personDTO.getRisks().forEach(riskDTO -> {
                    AgreementPersonRisksEntity agreementPersonRisksEntity = new AgreementPersonRisksEntity();
                    agreementPersonRisksEntity.setAgreementPerson(saveAgreementPerson(personDTO, agreementEntity));
                    agreementPersonRisksEntity.setRiskIc(riskDTO.getRiskIc());
                    agreementPersonRisksEntity.setPremium(riskDTO.getPremium());
                });
            }
        });
    }

    private AgreementPersonEntity saveAgreementPerson(PersonDTO personDTO,AgreementEntity agreementEntity){
        PersonEntity personEntity = personEntityFactory.createPersonEntity(personDTO);
        AgreementPersonEntity agreementPersonEntity = new AgreementPersonEntity();
        agreementPersonEntity.setAgreement(agreementEntity);
        agreementPersonEntity.setPerson(personEntity);
        agreementPersonEntity.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
        agreementPersonEntity.setTravelCost(personDTO.getTravelCost());
        agreementPersonEntity.setSportActivity(personDTO.getSportActivity());
        return agreementPersonEntity;
    }

}
