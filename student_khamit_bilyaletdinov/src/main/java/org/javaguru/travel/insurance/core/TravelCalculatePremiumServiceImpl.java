package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(calculateAgreementPrice(request.getAgreementDateFrom(),request.getAgreementDateTo()));
        return response;
    }

    @Override
    public BigDecimal calculateAgreementPrice(Date startDate, Date endDate) {
        LocalDate DateFrom = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate DateTo = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return BigDecimal.valueOf(ChronoUnit.DAYS.between(DateFrom,DateTo));
    }

}
