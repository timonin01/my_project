package org.javaguru.travel.insurance.core.repositories.entities;

import org.javaguru.travel.insurance.core.domain.entities.AgreementXmlExportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgreementXmlExportEntityRepository
        extends JpaRepository<AgreementXmlExportEntity,Long> {

    Boolean findByAgreementUuid(String uuid);

}
