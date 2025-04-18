package org.javaguru.blacklist.core.repositories;

import org.javaguru.blacklist.core.domain.BlackListedPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BlackListedPersonRepository extends JpaRepository<BlackListedPersonEntity,Long> {

    @Query("SELECT pe from BlackListedPersonEntity pe " +
        "where pe.personFirstName = :personFirstName " +
        "      and pe.personLastName = :personLastName " +
        "      and pe.personCode = :personCode")
    Optional<BlackListedPersonEntity> findBy(
        @Param("personFirstName") String personFirstName,
        @Param("personLastName") String personLastName,
        @Param("personCode") String personCode
    );

}
