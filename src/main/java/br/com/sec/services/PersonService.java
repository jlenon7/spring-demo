package br.com.sec.services;

import java.util.List;

import br.com.sec.models.Person;
import org.springframework.stereotype.Service;
import br.com.sec.exception.NotFoundException;
import br.com.sec.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person findById(String id) {
        return personRepository.findById(id).orElseThrow(() -> new NotFoundException("No records found for this ID"));
    }

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Person update(String id, Person person) {
        Person entity = findById(id);

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return create(entity);
    }

    public void delete(String id) {
        personRepository.delete(findById(id));
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

}