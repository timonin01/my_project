package org.javaguru.travel.insurance.core.repositories.entities;

import org.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import org.javaguru.travel.insurance.core.domain.entities.AgreementPersonEntity;
import org.javaguru.travel.insurance.core.domain.entities.AgreementPersonRisksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgreementPersonRisksEntityRepository
        extends JpaRepository<AgreementPersonRisksEntity,Long> {
    List<AgreementPersonRisksEntity> findByAgreementPerson(AgreementPersonEntity agreementPerson);

}
