package org.javaguru.travel.insurance.core.underwriting.calculators.medical.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class DayCountCalculator {

    private final DateTimeUtil dateTimeUtil;

    public BigDecimal calculateDayCount(TravelCalculatePremiumRequest request){
        var dayCount = dateTimeUtil.calculateDaysBetween(request.getAgreementDateFrom(),
                request.getAgreementDateTo());
        return new BigDecimal(dayCount);
    }

}
