package br.com.sec.controller;


import br.com.sec.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sec.models.vo.PersonVO;
import br.com.sec.services.PersonService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/api/v1/persons")
public class PersonControllerV1 {
    @Autowired
    private PersonService personService;

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<PersonVO> index() {
        List<PersonVO> persons = personService.findAll();
        persons.forEach(p -> p.add(linkTo(methodOn(PersonControllerV1.class).show(p.getKey())).withSelfRel()));

        return persons;
    }

    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO create(@RequestBody PersonVO person) {
        PersonVO personVo = personService.create(person);
        personVo.add(linkTo(methodOn(PersonControllerV1.class).show(personVo.getKey())).withSelfRel());

        return personVo;
    }

    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO show(@PathVariable("id") Long id) throws NotFoundException {
        PersonVO personVo = personService.findById(id);
        personVo.add(linkTo(methodOn(PersonControllerV1.class).show(id)).withSelfRel());

        return personVo;
    }

    @PutMapping(value = "/{id}", produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO update(@PathVariable("id") Long id, @RequestBody PersonVO person) throws NotFoundException {
        PersonVO personVo = personService.update(id, person);
        personVo.add(linkTo(methodOn(PersonControllerV1.class).show(personVo.getKey())).withSelfRel());

        return personVo;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws NotFoundException {
        personService.delete(id);

        return ResponseEntity.ok().build();
    }
}