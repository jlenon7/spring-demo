package br.com.sec.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import br.com.sec.models.Person;
import br.com.sec.services.PersonService;
import br.com.sec.exception.UnsupportedMathOperationException;

import java.util.List;

@RestController
@RequestMapping(value="/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Person> index() throws UnsupportedMathOperationException {
        return personService.findAll();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public Person show(@PathVariable("id") String id) throws UnsupportedMathOperationException {
        return personService.findById(id);
    }

    @RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person) throws UnsupportedMathOperationException {
        return personService.create(person);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public Person update(@PathVariable("id") String id, @RequestBody Person person) throws UnsupportedMathOperationException {
        return personService.update(id, person);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public void delete(@PathVariable("id") String id) throws UnsupportedMathOperationException {
        personService.delete(id);
    }
}