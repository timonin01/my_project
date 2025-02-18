package javaguru.travel.insurance.core.underwriting.calculators.medical.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import javaguru.travel.insurance.core.util.DateTimeUtil;
import javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class DayCountCalculator {

    private final DateTimeUtil dateTimeUtil;

    public BigDecimal calculateDayCount(TravelCalculatePremiumRequestV1 request){
        var dayCount = dateTimeUtil.calculateDaysBetween(request.getAgreementDateFrom(),
                request.getAgreementDateTo());
        return new BigDecimal(dayCount);
    }

}
