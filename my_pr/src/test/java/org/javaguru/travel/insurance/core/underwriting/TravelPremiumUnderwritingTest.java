package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
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
class TravelPremiumUnderwritingTest {

    @Mock
    DateTimeUtil dateTimeService;

    @InjectMocks
    private TravelPremiumUnderwritingImpl premiumUnderwriting;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        request = init();
        when(dateTimeService.calculateDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(0L);
    }

    @Test
    public void shouldTestAgreementPrice(){
        BigDecimal premium = premiumUnderwriting.calculateDaysBetween(request);
        assertEquals(premium, new BigDecimal(0));
    }



    public TravelCalculatePremiumRequest init(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Andrey");
        request.setPersonLastName("Timonin");
        request.setAgreementDateFrom(createDate("01.01.2025"));
        request.setAgreementDateTo(createDate("10.01.2025"));
        return request;
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}