package org.javaguru.travel.insurance.core.underwriting.integration.medical;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import org.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import org.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"medical.risk.limit.level.enabled=true"})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class MedicalRiskLimitIntegrationSwitchEnabledTest {

    @Autowired
    private TravelPremiumUnderwriting premiumUnderwriting;

    @Test
    public void shouldNotReturnError(){
        PersonDTO person = PersonDTOBuilder.createPerson().
                withPersonFirstName("Vasja").
                withPersonLastName("Pupkin").
                withPersonBirthDate(createDate("01.01.2000")).
                withMedicalRiskLimitLevel("LEVEL_20000").
                build();

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(createDate("01.01.2030"))
                .withDateTo(createDate("09.01.2030"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPersons(List.of(person))
                .build();

        TravelPremiumCalculationResult result = premiumUnderwriting.calculatePremium(agreement,person);
        assertEquals(result.getTotalPremium(),new BigDecimal("33.00"));
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
