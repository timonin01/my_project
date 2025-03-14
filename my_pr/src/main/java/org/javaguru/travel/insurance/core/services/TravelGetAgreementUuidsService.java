package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementUuidsCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementUuidsCoreResult;

public interface TravelGetAgreementUuidsService {

    TravelGetAgreementUuidsCoreResult getAllAgreement(TravelGetAgreementUuidsCoreCommand command);

}
