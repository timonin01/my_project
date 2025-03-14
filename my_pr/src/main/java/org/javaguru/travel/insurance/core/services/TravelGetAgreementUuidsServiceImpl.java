package org.javaguru.travel.insurance.core.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementUuidsCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementUuidsCoreResult;
import org.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelGetAgreementUuidsServiceImpl implements TravelGetAgreementUuidsService{

    private final AgreementEntityRepository repository;

    @Override
    public TravelGetAgreementUuidsCoreResult getAllAgreement(TravelGetAgreementUuidsCoreCommand command) {
        List<String> agreementUuids = repository.getAllAgreementUuids();
        return new TravelGetAgreementUuidsCoreResult(null, agreementUuids);
    }
}
