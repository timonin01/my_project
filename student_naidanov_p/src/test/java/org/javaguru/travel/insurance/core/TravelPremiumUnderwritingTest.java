package org.javaguru.travel.insurance.core;

import org.assertj.core.api.Assertions;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelPremiumUnderwritingTest {
    public static final long AWARD = 5L;
    @Mock private DateTimeService dateTimeService;
    @InjectMocks private TravelPremiumUnderwriting premiumUnderwriting;

    @Test
    void ifRequestIsValidShouldCalculatePriceCorrectly() {
        var request  = Mockito.mock(TravelCalculatePremiumRequest.class);
        when(dateTimeService.calculateDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(AWARD);

        var price = premiumUnderwriting.calculatePrice(request);

       Mockito.verify(dateTimeService).calculateDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());
       Assertions.assertThat(price).isEqualTo(new BigDecimal(AWARD));
    }
}