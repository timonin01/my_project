package org.javaguru.travel.insurance.core.underwriting.calculators.medical.medical;

import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DayCountCalculatorTest {

    @Mock private DateTimeUtil dateTimeUtil;

    @InjectMocks DayCountCalculator calculator;

    private TravelCalculatePremiumRequestV1 request;

    @BeforeEach
    public void setUp(){
        request = new TravelCalculatePremiumRequestV1();
        request.setAgreementDateFrom(createDate("01.01.2025"));
        request.setAgreementDateTo(createDate("10.01.2025"));
    }

    @Test
    public void sholdCalculateDaysBetween(){
        long exceptedBigDecimal = 9;
        when(dateTimeUtil.calculateDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo()))
                .thenReturn(exceptedBigDecimal);
        BigDecimal result = calculator.calculateDayCount(request);
        assertEquals(BigDecimal.valueOf(exceptedBigDecimal), result);
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}