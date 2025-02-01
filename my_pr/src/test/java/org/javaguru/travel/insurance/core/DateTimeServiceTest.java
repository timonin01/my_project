package org.javaguru.travel.insurance.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeServiceTest {

    private DateTimeService dateTimeService;

    @BeforeEach
    void setUp() {
        dateTimeService = new DateTimeService();
    }
    @Test
    void calculateDaysBetweenBeZero() {
        Date d1 = createDate("01.01.2022");
        Date d2 = createDate("01.01.2022");
        var daysBetween = dateTimeService.calculateDaysBetween(d1, d2);
        assertEquals(daysBetween, 0L);
    }

    @Test
    void calculateDaysBetweenBeNegative() {
        Date d1 = createDate("10.01.2022");
        Date d2 = createDate("01.01.2022");
        var daysBetween = dateTimeService.calculateDaysBetween(d1, d2);
        assertEquals(daysBetween, -9L);
    }

    @Test
    void calculateDaysBetweenBePozitive() {
        Date d1 = createDate("01.01.2022");
        Date d2 = createDate("10.01.2022");
        var daysBetween = dateTimeService.calculateDaysBetween(d1, d2);
        assertEquals(daysBetween, 9L);
    }




    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}