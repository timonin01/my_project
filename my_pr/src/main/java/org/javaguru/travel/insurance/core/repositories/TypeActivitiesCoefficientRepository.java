package org.javaguru.travel.insurance.core.repositories;

import org.javaguru.travel.insurance.core.domain.TMAgeCoefficient;
import org.javaguru.travel.insurance.core.domain.TypeActivitiesCoefficient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TypeActivitiesCoefficientRepository extends JpaRepository<TypeActivitiesCoefficient,Long> {

    @Cacheable(cacheNames = {"typeActivitiesCoefficientCache"}, key = "#p0", unless="#result == null")
    @Query("SELECT tc from TypeActivitiesCoefficient tc " +
        "where tc.sportActivity = :sportActivity")
    Optional<TypeActivitiesCoefficient> findCoefficient(@Param("sportActivity") String sportActivity);

}
