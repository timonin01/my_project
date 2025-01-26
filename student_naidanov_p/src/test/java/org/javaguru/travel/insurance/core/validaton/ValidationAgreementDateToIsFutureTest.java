package org.javaguru.travel.insurance.core.validaton;

import org.assertj.core.api.Assertions;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ValidationAgreementDateToIsFutureTest {

    private final ValidationAgreementDateToIsFuture validator = new ValidationAgreementDateToIsFuture();

    @Test
    void ifAgreementDateToIsFutureShouldReturnOK() {
        var request = Mockito.mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(LocalDate.now().plusDays(1));

        var result = validator.execute(request);

        Assertions.assertThat(result).isEmpty();
    }
}
