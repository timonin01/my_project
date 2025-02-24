package org.javaguru.travel.insurance.core.api.dto;

import java.math.BigDecimal;

public class RiskDTOBuilder {

    private String riskIc;
    private BigDecimal premium;

    public static RiskDTOBuilder createRisk(){return new RiskDTOBuilder();}

    public RiskDTO build(){
        RiskDTO risk = new RiskDTO();
        risk.setRiskIc(riskIc);
        risk.setPremium(premium);
        return risk;
    }

    public RiskDTOBuilder withRiskIc(String riskIc){
        this.riskIc = riskIc;
        return this;
    }

    public RiskDTOBuilder withPremium(BigDecimal premium){
        this.premium = premium;
        return this;
    }
}
