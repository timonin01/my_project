package org.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


class DateTimeServiceImplTest {
    DateTimeServiceImpl dateTimeService = new DateTimeServiceImpl();

    @Test
    public void calculateAgreementPriceWorkCorrect(){
        BigDecimal price =  dateTimeService.calculateAgreementPrice(new Date(2025, Calendar.JANUARY,23),new Date(2025, Calendar.JANUARY,24));
        Assertions.assertEquals(new BigDecimal("1"),price);

    }

}