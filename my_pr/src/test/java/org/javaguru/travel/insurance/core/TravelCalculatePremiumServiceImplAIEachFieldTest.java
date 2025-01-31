package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

class TravelCalculatePremiumServiceImplAIEachFieldTest {

    @Test
    void testCalculatePremium_AgreementDateFrom() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();

        Date dateFrom = new Date(124, 0, 1);  // 2024-01-01
        request.setAgreementDateFrom(dateFrom);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(dateFrom, response.getAgreementDateFrom());
    }

    @Test
    void testCalculatePremium_AgreementDateTo() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();

        Date dateTo = new Date(124, 11, 31); // 2024-12-31
        request.setAgreementDateTo(dateTo);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(dateTo, response.getAgreementDateTo());
    }

    @Test
    void testCalculatePremium_PersonFirstName() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals("John", response.getPersonFirstName());
    }

    @Test
    void testCalculatePremium_PersonLastName() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Doe");

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals("Doe", response.getPersonLastName());
    }
}