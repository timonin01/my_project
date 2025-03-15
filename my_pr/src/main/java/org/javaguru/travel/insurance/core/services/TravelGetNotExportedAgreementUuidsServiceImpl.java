package org.javaguru.travel.insurance.core.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreResult;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelGetNotExportedAgreementUuidsServiceImpl implements TravelGetNotExportedAgreementUuidsService {

    private final AgreementEntityRepository repository;

    @Override
    public TravelGetNotExportedAgreementUuidsCoreResult getAllAgreement(TravelGetNotExportedAgreementUuidsCoreCommand command) {
        List<String> agreementUuids = repository.getAllAgreementUuids();
        return new TravelGetNotExportedAgreementUuidsCoreResult(null, agreementUuids);
    }
}
