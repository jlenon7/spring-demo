package br.com.sec.controller;

import br.com.sec.models.vo.PersonVOV2;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sec.services.PersonService;


@RestController
@RequestMapping(value="/v2/persons")
public class PersonControllerV2 {

    @Autowired
    private PersonService personService;

    @PostMapping
    public PersonVOV2 create(@RequestBody PersonVOV2 person) {
        return personService.createV2(person);
    }
}