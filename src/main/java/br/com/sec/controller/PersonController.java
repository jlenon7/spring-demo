package br.com.sec.controller;


import br.com.sec.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sec.models.Person;
import br.com.sec.services.PersonService;
import br.com.sec.exception.UnsupportedMathOperationException;

import java.util.List;

@RestController
@RequestMapping(value="/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> index() throws UnsupportedMathOperationException {
        return personService.findAll();
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @GetMapping("/{id}")
    public Person show(@PathVariable("id") Long id) throws NotFoundException {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable("id") Long id, @RequestBody Person person) throws NotFoundException {
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws NotFoundException {
        personService.delete(id);

        return ResponseEntity.ok().build();
    }
}