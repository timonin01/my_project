package org.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class DateTimeServiceImpl implements DateTimeService {

    @Override
    public BigDecimal calculateAgreementPrice(Date startDate, Date endDate) {
        LocalDate DateFrom = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate DateTo = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return BigDecimal.valueOf(ChronoUnit.DAYS.between(DateFrom,DateTo));
    }
}
