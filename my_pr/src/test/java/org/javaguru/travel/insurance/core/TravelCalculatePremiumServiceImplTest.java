package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
    private TravelCalculatePremiumRequest request;

    @BeforeEach
     void setUp() {
        request = init();
    }

    @Test
    public void shouldTestDateTo() {
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
        assertNotNull(response.getAgreementDateTo(), "Agreement Date To is null");
    }

    @Test
    public void shouldTestFirstName(){
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
        assertNotNull(response.getPersonFirstName(), "Agreement FirstName To is null");
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
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
        return request;
    }
}