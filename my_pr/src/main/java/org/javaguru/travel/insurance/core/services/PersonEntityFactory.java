package org.javaguru.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import org.javaguru.travel.insurance.core.repositories.entities.PersonEntityRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PersonEntityFactory {

    private final PersonEntityRepository personEntityRepository;

    PersonEntity createPersonEntity(PersonDTO person){
        Optional<PersonEntity> personOpt = personEntityRepository.findBy(
                person.getPersonFirstName(),
                person.getPersonLastName(),
                person.getPersonCode());
        if (personOpt.isPresent()) {
            return personOpt.get();
        } else {
            PersonEntity person2 = new PersonEntity();
            person2.setFirstName(person.getPersonFirstName());
            person2.setLastName(person.getPersonLastName());
            person2.setPersonCode(person.getPersonCode());
            person2.setBirthDate(person.getPersonBirthDate());
            return personEntityRepository.save(person2);
        }
    }

}
