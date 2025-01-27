package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.math.BigDecimal;
import java.util.Date;

public interface TravelCalculatePremiumService {

    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);
    public BigDecimal calculateAgreementPrice(Date startDate, Date endDate);

}
