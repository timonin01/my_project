package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {
    private TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
    TravelCalculatePremiumRequest request;
    @BeforeEach
    public void createRequest(){
        request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("NAMETEST");
        request.setPersonLastName("LASTNAMETEST");
        request.setAgreementDateFrom(new Date(2025,Calendar.JANUARY,30));
        request.setAgreementDateTo(new Date(2025,Calendar.JANUARY,29));
    }
    @Test
    public void deleteMe() {

    }

    @Test
    public void calculatePremiumFirstNameTest(){
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }
    @Test
    public void calculatePremiumLastNameTest(){
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
    }
    @Test
    public void calculatePremiumLDateFromTest(){
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }
    @Test
    public void calculatePremiumLDateToTest(){
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }
}