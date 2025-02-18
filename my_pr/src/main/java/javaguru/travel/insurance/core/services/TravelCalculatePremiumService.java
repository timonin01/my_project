package javaguru.travel.insurance.core.services;

import javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponseV1 calculatePremium(TravelCalculatePremiumRequestV1 request);

}
