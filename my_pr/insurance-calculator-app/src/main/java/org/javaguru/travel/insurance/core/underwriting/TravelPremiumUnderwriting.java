package  org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;

public interface TravelPremiumUnderwriting {
    public TravelPremiumCalculationResult calculatePremium
            (AgreementDTO agreement, PersonDTO personDTO);
}
