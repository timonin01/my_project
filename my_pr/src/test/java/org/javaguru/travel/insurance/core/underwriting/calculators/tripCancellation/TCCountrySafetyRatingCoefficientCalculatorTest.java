package org.javaguru.travel.insurance.core.underwriting.calculators.tripCancellation;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.domain.TCCountrySafetyRatingCoefficient;
import org.javaguru.travel.insurance.core.repositories.TCCountrySafetyRatingCoefficientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TCCountrySafetyRatingCoefficientCalculatorTest {

    @Mock private TCCountrySafetyRatingCoefficientRepository countrySafetyRatingCoefficientRepository;

    @InjectMocks private TCCountrySafetyRatingCoefficientCalculator countrySafetyRatingCoefficientCalculator;

    private AgreementDTO agreementDTO;

    @BeforeEach
    public void setUp(){
        agreementDTO = new AgreementDTO();
        agreementDTO.setCountry("LATVIA");
    }

    @Test
    void shouldFindCoefficientWhenAgeCoefficientExists() {
        BigDecimal exceptedValue = BigDecimal.valueOf(5);

        TCCountrySafetyRatingCoefficient tcCountrySafetyRatingCoefficient = mock(TCCountrySafetyRatingCoefficient.class);
        when(tcCountrySafetyRatingCoefficient.getCoefficient()).thenReturn(exceptedValue);

        when(countrySafetyRatingCoefficientRepository.findByCountryIc(agreementDTO.getCountry()))
                .thenReturn(Optional.of(tcCountrySafetyRatingCoefficient));

        BigDecimal result = countrySafetyRatingCoefficientCalculator
                .calculateCountryCoefficient(agreementDTO);

        assertEquals(exceptedValue,result);
    }

    @Test
    void shouldThrowExceptionWhenTravelCostCoefficientNotFound() {
        when(countrySafetyRatingCoefficientRepository.findByCountryIc(agreementDTO.getCountry())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> countrySafetyRatingCoefficientCalculator.calculateCountryCoefficient(agreementDTO));
        assertEquals("Country safety rating coefficient not found by country id = " + agreementDTO.getCountry(), exception.getMessage());
    }
}