package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class DayCountCalculator {

    private final DateTimeUtil dateTimeUtil;

    public BigDecimal calculateDayCount(AgreementDTO agreement){
        var dayCount = dateTimeUtil.calculateDaysBetween(agreement.getAgreementDateFrom(),
                agreement.getAgreementDateTo());
        return new BigDecimal(dayCount);
    }

}
