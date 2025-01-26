package org.javaguru.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.validaton.RequestValidator;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final RequestValidator requestValidator;
    private final TravelPremiumUnderwriting travelPremiumUnderwriting;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> validationErrors = requestValidator.validateRequest(request);
        return validationErrors.isEmpty() ? buildTravelPremiumResponse(request, travelPremiumUnderwriting.calculatePrice(request)) :
                buildTravelPremiumResponse(validationErrors);
    }

    private TravelCalculatePremiumResponse buildTravelPremiumResponse(TravelCalculatePremiumRequest request, BigDecimal price) {
        var response = TravelCalculatePremiumResponse.builder();
        response.personFirstName(request.getPersonFirstName());
        response.personLastName(request.getPersonLastName());
        response.agreementDateTo(request.getAgreementDateTo());
        response.agreementDateFrom(request.getAgreementDateFrom());
        response.agreementPrice(price);
        return response.build();
    }

    private TravelCalculatePremiumResponse buildTravelPremiumResponse(List<ValidationError> validationErrors) {
        return new TravelCalculatePremiumResponse(validationErrors);
    }
}
