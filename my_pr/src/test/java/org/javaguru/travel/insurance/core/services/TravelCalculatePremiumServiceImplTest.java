package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock private TravelCalculatePremiumRequestValidator requestValidator;
    @Mock private TravelPremiumUnderwriting premiumUnderwriting;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
     void setUp() {
        request = init();
        when(requestValidator.validate(request)).thenReturn(List.of());
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.TEN);
    }

    @Test
    public void shouldTestDateTo() {
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    public void shouldTestFirstName(){
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }

    @Test
    public void shouldTestLastName(){
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
    }

    @Test
    public void shouldTestDateFrom(){
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @Test
    public void shouldTestAgreementPrice(){
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertNotNull(response.getAgreementPrice());
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