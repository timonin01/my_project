package org.javaguru.travel.insurance.core.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonDTOBuilder {

    private String personFirstName;
    private String personLastName;
    private Date personBirthDate;
    private String medicalRiskLimitLevel;
    private List<RiskDTO> risks = new ArrayList<>();

    public static PersonDTOBuilder createPerson(){return new PersonDTOBuilder();}

    public PersonDTO build(){
        PersonDTO person = new PersonDTO();
        person.setPersonFirstName(personFirstName);
        person.setPersonLastName(personLastName);
        person.setPersonBirthDate(personBirthDate);
        person.setMedicalRiskLimitLevel(medicalRiskLimitLevel);
        person.setRisks(risks);
        return person;
    }

    public PersonDTOBuilder withPersonFirstName(String personFirstName){
        this.personFirstName = personFirstName;
        return this;
    }

    public PersonDTOBuilder withPersonLastName(String personLastName){
        this.personLastName = personLastName;
        return this;
    }

    public PersonDTOBuilder withPersonBirthDate(Date personBirthDate){
        this.personBirthDate = personBirthDate;
        return this;
    }

    public PersonDTOBuilder withMedicalRiskLimitLevel(String medicalRiskLimitLevel){
        this.medicalRiskLimitLevel = medicalRiskLimitLevel;
        return this;
    }

    public PersonDTOBuilder withRisk(RiskDTO riskDTO) {
        this.risks.add(riskDTO);
        return this;
    }

    public PersonDTOBuilder withRisks(List<RiskDTO> risks) {
        this.risks = risks;
        return this;
    }

}
