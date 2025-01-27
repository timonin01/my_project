package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

    @Test
    public void CalculatePremiumFirstNameCorrect(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Khamit");
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }
    @Test
    public void CalculatePremiumLastNameCorrect(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Bil");
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }
    @Test
    public void CalculatePremiumDateToCorrect(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(new Date(2025, Calendar.JANUARY,24));
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }
    @Test
    public void CalculatePremiumDateFromCorrect(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(new Date(2025, Calendar.JANUARY,23));
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }



}