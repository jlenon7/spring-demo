package br.com.sec.controller;


import br.com.sec.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sec.models.vo.PersonVO;
import br.com.sec.services.PersonService;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/persons")
public class PersonControllerV1 {
    @Autowired
    private PersonService personService;

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<PersonVO> index() {
        return personService.findAll();
    }

    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO create(@RequestBody PersonVO person) {
        return personService.create(person);
    }

    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO show(@PathVariable("id") Long id) throws NotFoundException {
        return personService.findById(id);
    }

    @PutMapping(value = "/{id}", produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO update(@PathVariable("id") Long id, @RequestBody PersonVO person) throws NotFoundException {
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws NotFoundException {
        personService.delete(id);

        return ResponseEntity.ok().build();
    }
}