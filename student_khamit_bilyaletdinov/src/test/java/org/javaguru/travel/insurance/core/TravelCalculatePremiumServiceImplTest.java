package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
    private TravelCalculatePremiumRequest request;
    @BeforeEach
    public void createRequest() {
        request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(new Date(2025, Calendar.JANUARY,24));
        request.setAgreementDateFrom(new Date(2025, Calendar.JANUARY,23));
        request.setPersonFirstName("Khamit");
        request.setPersonLastName("Bil");
    }

    @Test
    public void calculatePremiumFirstNameCorrect(){
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }
    @Test
    public void calculatePremiumLastNameCorrect(){


        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }
    @Test
    public void calculatePremiumDateToCorrect(){

        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals(request.getAgreementDateTo(),response.getAgreementDateTo());
    }
    @Test
    public void calculatePremiumDateFromCorrect(){
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals( request.getAgreementDateFrom(),response.getAgreementDateFrom());
    }

    @Test
    public void calculatePremiumAgreementPriceCorrect(){
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals(new BigDecimal("1"),response.getAgreementPrice());
    }
    @Test
    public void calculateAgreementPriceWorkCorrect(){
       BigDecimal price =  service.calculateAgreementPrice(new Date(2025, Calendar.JANUARY,23),new Date(2025, Calendar.JANUARY,24));
        Assertions.assertEquals(new BigDecimal("1"),price);

    }




}