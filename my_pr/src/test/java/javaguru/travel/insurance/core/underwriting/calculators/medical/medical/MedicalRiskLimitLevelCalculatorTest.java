package javaguru.travel.insurance.core.underwriting.calculators.medical.medical;

import javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MedicalRiskLimitLevelCalculatorTest {

    @Autowired private MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    @Test
    @DisplayName("Test:  MedicalRiskLimitLevel table is present")
    public void injectedRepositoryAreNotNull() {
        assertNotNull(medicalRiskLimitLevelRepository);
    }

    @Test
    @DisplayName("Test: should find coefficient")
    public void shouldFindCoefficient(){
        Optional<MedicalRiskLimitLevel> medicalRiskLimitLevel = medicalRiskLimitLevelRepository
                .findByMedicalRiskLimitLevelIc("LEVEL_15000");
        BigDecimal excepted = new BigDecimal(1.20);
        excepted = excepted.setScale(2, RoundingMode.HALF_UP);
        BigDecimal decimal = medicalRiskLimitLevel.get().getCoefficient();
        assertTrue(medicalRiskLimitLevel.isPresent());
        assertEquals(excepted,decimal);
    }

    @Test
    @DisplayName("Test: should not find coefficient")
    public void shouldNotFindCoefficient(){
        Optional<MedicalRiskLimitLevel> medicalRiskLimitLevel = medicalRiskLimitLevelRepository
                .findByMedicalRiskLimitLevelIc("LEVEL_12345");
        assertTrue(medicalRiskLimitLevel.isEmpty());
    }

}