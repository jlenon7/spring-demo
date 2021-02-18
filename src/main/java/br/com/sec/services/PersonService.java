package br.com.sec.services;

import java.util.List;

import br.com.sec.adapters.DozerAdapter;
import br.com.sec.models.Person;
import br.com.sec.models.vo.PersonVO;
import org.springframework.stereotype.Service;
import br.com.sec.exception.NotFoundException;
import br.com.sec.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public PersonVO findById(Long id) {
        var entity = personRepository.findById(id).orElseThrow(() -> new NotFoundException("No records found for this ID"));

        return DozerAdapter.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        var entity = DozerAdapter.parseObject(person, Person.class);

        return DozerAdapter.parseObject(personRepository.save(entity), PersonVO.class);
    }

    public PersonVO update(Long id, PersonVO person) {
        PersonVO entity = findById(id);

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return create(entity);
    }

    public void delete(Long id) {
        personRepository.delete(DozerAdapter.parseObject(findById(id), Person.class));
    }

    public List<PersonVO> findAll() {
        return DozerAdapter.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

}