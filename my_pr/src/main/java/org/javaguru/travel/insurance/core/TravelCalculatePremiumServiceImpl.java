package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {

        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();

        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());

        var daysBetween = calculateDays(request);
        response.setAgreementPrice(new BigDecimal(daysBetween));

        return response;
    }

    public long calculateDays(TravelCalculatePremiumRequest request){
        long diff = request.getAgreementDateFrom().getTime() - request.getAgreementDateTo().getTime();
        var daysBetween = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return daysBetween;
    }
}
