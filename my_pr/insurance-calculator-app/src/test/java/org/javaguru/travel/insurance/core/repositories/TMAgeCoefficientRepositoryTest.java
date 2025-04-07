package org.javaguru.travel.insurance.core.repositories;

import org.javaguru.travel.insurance.core.domain.TMAgeCoefficient;
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
class TMAgeCoefficientRepositoryTest {

    @Autowired private TMAgeCoefficientRepository ageCoefficientRepository;

    @Test
    @DisplayName("Test: Classifier table is present")
    public void injectedRepositoryAreNotNull() {
        assertNotNull(ageCoefficientRepository);
    }

    @Test
    @DisplayName("Test: should find coefficient where age = 3 ")
    public void shouldFindCoefficient(){
        Optional<TMAgeCoefficient> coefficient= ageCoefficientRepository.findCoefficient(3);
        BigDecimal bigDecimal =new BigDecimal(1.10);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        assertTrue(coefficient.isPresent());
        assertEquals(coefficient.get().getCoefficient(),bigDecimal);
    }

    @Test
    @DisplayName("Test: should not find coefficient where age in incorrect ")
    public void shouldNotFindCoefficient(){
        Optional<TMAgeCoefficient> coefficient= ageCoefficientRepository.findCoefficient(-1);
        assertTrue(coefficient.isEmpty());
    }

}
