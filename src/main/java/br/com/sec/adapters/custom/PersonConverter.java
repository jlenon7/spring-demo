package br.com.sec.adapters.custom;

import br.com.sec.models.Person;
import br.com.sec.models.vo.PersonVOV2;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonConverter {
    public PersonVOV2 convertEntityToVO(Person person) {
        PersonVOV2 vo = new PersonVOV2();

        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setBirthdate(new Date());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());

        return vo;
    }

    public Person convertVOToEntity(PersonVOV2 person) {
        Person entity = new Person();

        entity.setId(person.getId());
        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());

        return entity;
    }
}
