package org.javaguru.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelPremiumUnderwriting {

    private DateTimeService dateTimeService;

    public BigDecimal calculateDaysBetween(TravelCalculatePremiumRequest request){
        var daysBetween =  dateTimeService.calculateDaysBetween(request.getAgreementDateFrom(),
                request.getAgreementDateTo());
        return new BigDecimal(daysBetween);
    }

}
