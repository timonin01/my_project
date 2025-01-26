package org.javaguru.travel.insurance.core;

import org.assertj.core.api.Assertions;
import org.javaguru.travel.insurance.core.validaton.RequestValidator;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {
    @Mock
    private TravelPremiumUnderwriting travelPremiumUnderwriting;
    @Mock
    private RequestValidator requestValidator;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl travelCalculatePremiumService;

    public static final LocalDate DATE_FROM = LocalDate.of(2023, 1, 1);
    public static final LocalDate DATE_TO = LocalDate.of(2023, 1, 5);

    @Test
    void shouldCalculatePremiumCorrect() {
        var request = createCorrectRequest();

        var premium = travelCalculatePremiumService.calculatePremium(request);

        Assertions.assertThat(premium.hasErrors()).isFalse();
        Mockito.verify(travelPremiumUnderwriting).calculatePrice(request);
        Assertions.assertThat(premium.getPersonFirstName()).isEqualTo("John");
        Assertions.assertThat(premium.getPersonLastName()).isEqualTo("Doe");
        Assertions.assertThat(premium.getAgreementDateFrom()).isEqualTo(DATE_FROM);
        Assertions.assertThat(premium.getAgreementDateTo()).isEqualTo(DATE_TO);
    }

    @Test
    void shouldCalculatePremiumWithErrors() {
        var request = Mockito.mock(TravelCalculatePremiumRequest.class);
        when(requestValidator.validateRequest(request)).thenReturn(List.of(new ValidationError("field", "error")));

        var premium = travelCalculatePremiumService.calculatePremium(request);

        Assertions.assertThat(premium.hasErrors()).isTrue();
        Assertions.assertThat(premium.getErrors()).hasSize(1);
        Assertions.assertThat(premium.getErrors().getFirst().getField()).isEqualTo("field");
        Assertions.assertThat(premium.getErrors().getFirst().getMessage()).isEqualTo("error");
        Mockito.verifyNoInteractions(travelPremiumUnderwriting);
        Assertions.assertThat(premium.getAgreementPrice()).isNull();
        Assertions.assertThat(premium.getPersonFirstName()).isNull();
        Assertions.assertThat(premium.getPersonLastName()).isNull();
        Assertions.assertThat(premium.getAgreementDateFrom()).isNull();
        Assertions.assertThat(premium.getAgreementDateTo()).isNull();

    }

    private TravelCalculatePremiumRequest createCorrectRequest() {
        var request = Mockito.mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(DATE_FROM);
        when(request.getAgreementDateTo()).thenReturn(DATE_TO);
        when(request.getPersonFirstName()).thenReturn("John");
        when(request.getPersonLastName()).thenReturn("Doe");
        when(requestValidator.validateRequest(request)).thenReturn(List.of());
        return request;
    }

}
