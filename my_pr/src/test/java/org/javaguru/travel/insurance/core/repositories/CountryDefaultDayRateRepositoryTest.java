package org.javaguru.travel.insurance.core.repositories;

import org.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class CountryDefaultDayRateRepositoryTest {

    @Autowired
    private CountryDefaultDayRateRepository repository;

    @Test
    @DisplayName("Test: Classifier table is present")
    public void injectedRepositoryAreNotNull() {
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Test: Can find record by country")
    public void shouldReturnCountryDefaultDayRate(){
        Optional<CountryDefaultDayRate> record = repository.findByCountryIc("JAPAN");
        assertTrue(record.isPresent());
        assertEquals(record.get().getCountryIc(), "JAPAN");
    }

    @Test
    @DisplayName("Test: Can not  find record by wrong country")
    public void shouldNotReturnCountryDefaultDayRate(){
        Optional<CountryDefaultDayRate> record = repository.findByCountryIc("JAPANS");
        assertTrue(record.isEmpty());
    }

}