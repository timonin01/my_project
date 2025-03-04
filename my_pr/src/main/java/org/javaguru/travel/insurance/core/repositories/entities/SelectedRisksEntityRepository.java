package org.javaguru.travel.insurance.core.repositories.entities;

import org.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import org.javaguru.travel.insurance.core.domain.entities.SelectedRisksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SelectedRisksEntityRepository extends JpaRepository<SelectedRisksEntity,Long> {
    List<SelectedRisksEntity> findByAgreement(AgreementEntity agreement);
}
