package org.javaguru.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.javaguru.travel.insurance.core.domain.entities.*;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementPersonEntityRepository;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementPersonRisksEntityRepository;
import org.javaguru.travel.insurance.core.repositories.entities.SelectedRisksEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDTOLoader {

    private final AgreementEntityRepository agreementEntityRepository;
    private final SelectedRisksEntityRepository selectedRiskEntityRepository;
    private final AgreementPersonEntityRepository agreementPersonEntityRepository;
    private final AgreementPersonRisksEntityRepository agreementPersonRiskEntityRepository;

    AgreementDTO load(String uuid) {
        AgreementDTO agreement = new AgreementDTO();
        AgreementEntity agreementEntity = agreementEntityRepository.findByUuid(uuid).get();
        loadAgreementFields(agreement,agreementEntity);
        loadPersons(agreement,agreementEntity);
        loadSelectedRisks(agreement,agreementEntity);
        return agreement;
    }

    private void loadAgreementFields(AgreementDTO agreementDTO, AgreementEntity agreement) {
        agreementDTO.setUuid(agreement.getUuid());
        agreementDTO.setCountry(agreement.getCountry());
        agreementDTO.setAgreementDateTo(agreement.getAgreementDateTo());
        agreementDTO.setAgreementDateFrom(agreement.getAgreementDateFrom());
        agreementDTO.setAgreementPremium(agreement.getAgreementPremium());
    }

    private void loadPersons(AgreementDTO agreementDTO, AgreementEntity agreement) {
        List<AgreementPersonEntity> personEntities = agreementPersonEntityRepository.findByAgreement(agreement);
        List<PersonDTO> personDTOS = personEntities.stream().map(agreementPerson->{
            PersonDTO personDTO = new PersonDTO();
            personDTO.setPersonFirstName(agreementPerson.getPerson().getFirstName());
            personDTO.setPersonLastName(agreementPerson.getPerson().getLastName());
            personDTO.setPersonBirthDate(agreementPerson.getPerson().getBirthDate());
            personDTO.setPersonCode(agreementPerson.getPerson().getPersonCode());
            personDTO.setMedicalRiskLimitLevel(agreementPerson.getMedicalRiskLimitLevel());

            personDTO.setRisks(
                    agreementPersonRiskEntityRepository.findByAgreementPerson(agreementPerson).stream().
                            map(agreementPersonRisksEntity->{
                                RiskDTO riskDTO= new RiskDTO();
                                riskDTO.setPremium(agreementPersonRisksEntity.getPremium());
                                riskDTO.setRiskIc(agreementPersonRisksEntity.getRiskIc());
                                return riskDTO;
                            }).collect(Collectors.toList())
            );
            return personDTO;
        }).collect(Collectors.toList());
        agreementDTO.setPersons(personDTOS);
    }

    private void loadSelectedRisks(AgreementDTO agreementDTO, AgreementEntity agreement) {
        List<SelectedRisksEntity> risksEntities = selectedRiskEntityRepository.findByAgreement(agreement);
        List<String> risks = risksEntities.stream().map(SelectedRisksEntity::getRiskIc)
                .collect(Collectors.toList());
        agreementDTO.setSelectedRisks(risks);
    }

}
