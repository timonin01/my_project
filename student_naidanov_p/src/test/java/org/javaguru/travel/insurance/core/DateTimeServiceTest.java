package org.javaguru.travel.insurance.core;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DateTimeServiceTest {

    public static final int ACTUAL_DIFF = 1;
    public static final LocalDate DATE_TO = LocalDate.of(2002, Month.JANUARY, 2);
    public static final LocalDate DATE_FROM = LocalDate.of(2002, Month.JANUARY, 1);

    @Test
    public void shouldCalculateDaysBetween() {
        var dateTimeService = new DateTimeService();

        var diff = dateTimeService.calculateDaysBetween(DATE_TO, DATE_FROM);

        Assertions.assertThat(diff).isEqualTo(ACTUAL_DIFF);
    }

    @Test
    public void shouldReturnZeroIfDatesAreEqual() {
        var dateTimeService = new DateTimeService();

        var diff = dateTimeService.calculateDaysBetween(DATE_TO, DATE_TO);

        Assertions.assertThat(diff).isEqualTo(0);
    }

}
