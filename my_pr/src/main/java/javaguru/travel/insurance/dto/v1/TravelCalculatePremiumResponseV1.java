package javaguru.travel.insurance.dto.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javaguru.travel.insurance.dto.util.BigDecimalSerializer;
import javaguru.travel.insurance.dto.CoreResponse;
import javaguru.travel.insurance.dto.RiskPremium;
import javaguru.travel.insurance.dto.ValidationError;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponseV1 extends CoreResponse {

    private String personFirstName;
    private String personLastName;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date personBirthDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateFrom;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateTo;

    private String medicalRiskLimitLevel;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal agreementPremium;

    private String country;

    private List<RiskPremium> risks;

    public TravelCalculatePremiumResponseV1(List<ValidationError> errors) {
        super(errors);
    }


}
