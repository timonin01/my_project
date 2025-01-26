package org.javaguru.travel.insurance.core.validaton;

import java.util.Arrays;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class RequestValidatorImplTest {

    @Mock
    private Validation validation1;
    @Mock
    private Validation validation2;

    private RequestValidatorImpl requestValidator;

    @Test
    void listOfErrorsShouldBeNotEmptyIfNotValid() {
        when(validation1.execute(Mockito.any())).thenReturn(Optional.of(new ValidationError()));
        when(validation2.execute(Mockito.any())).thenReturn(Optional.of(new ValidationError()));
        var validations = Arrays.asList(validation1, validation2);
        requestValidator = new RequestValidatorImpl(validations);
        var request = Mockito.mock(TravelCalculatePremiumRequest.class);

        var listOfErrors = requestValidator.validateRequest(request);

        Assertions.assertThat(listOfErrors).hasSize(2);
    }

    @Test
    void listOfErrorsShouldBeEmptyIfRequestIsValid() {
        when(validation1.execute(Mockito.any())).thenReturn(Optional.empty());
        when(validation2.execute(Mockito.any())).thenReturn(Optional.empty());
        var validations = Arrays.asList(validation1, validation2);
        requestValidator = new RequestValidatorImpl(validations);
        var request = Mockito.mock(TravelCalculatePremiumRequest.class);

        var listOfErrors = requestValidator.validateRequest(request);

        Assertions.assertThat(listOfErrors).isEmpty();
    }

}
