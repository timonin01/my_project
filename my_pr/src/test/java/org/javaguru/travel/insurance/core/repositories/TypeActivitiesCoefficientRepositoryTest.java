package org.javaguru.travel.insurance.core.repositories;

import org.javaguru.travel.insurance.core.domain.TMCountryDefaultDayRate;
import org.javaguru.travel.insurance.core.domain.TypeActivitiesCoefficient;
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
class TypeActivitiesCoefficientRepositoryTest {

    @Autowired
    private TypeActivitiesCoefficientRepository repository;

    @Test
    @DisplayName("Test: TypeActivitiesCoefficient table is present")
    public void injectedRepositoryAreNotNull() {
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Test: Can find record by country")
    public void shouldReturnCountryDefaultDayRate(){
        Optional<TypeActivitiesCoefficient> record = repository.findCoefficient("DIVING");
        assertTrue(record.isPresent());
        assertEquals(record.get().getCoefficient(), BigDecimal.valueOf(3.50).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    @DisplayName("Test: Can not  find record by wrong country")
    public void shouldNotReturnCountryDefaultDayRate(){
        Optional<TypeActivitiesCoefficient> record = repository.findCoefficient("DIVINGG");
        assertTrue(record.isEmpty());
    }

}
