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
public class PersonSaver {

    private final PersonEntityRepository repository;

    PersonEntity savePerson(PersonDTO person){
        Optional<PersonEntity> personEntity = repository.findBy(
                person.getPersonFirstName(),
                person.getPersonLastName(),
                person.getPersonCode()
        );
        if(personEntity.isPresent()){
            return personEntity.get();
        }
        PersonEntity personEn = new PersonEntity();
        personEn.setFirstName(person.getPersonFirstName());
        personEn.setLastName(person.getPersonLastName());
        personEn.setPersonCode(person.getPersonCode());
        personEn.setBirthDate(person.getPersonBirthDate());
        return repository.save(personEn);
    }

}
