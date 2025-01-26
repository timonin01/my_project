package org.javaguru.travel.insurance.core;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
class DateTimeService {
    DateTimeService() {
    }

   long calculateDaysBetween(LocalDate dateFrom, LocalDate dateTo) {
        return ChronoUnit.DAYS.between(dateTo, dateFrom);
    }
}
