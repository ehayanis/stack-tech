package com.xeigna.ms.demo.controller;

import com.xeigna.ms.demo.model.Person;
import com.xeigna.ms.demo.service.PersonService;
import com.xeigna.ms.demo.util.CustomErrorType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class PersonController {

    // public static final Logger logger = LoggerFactory.getLogger();

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> findAll() {
        List<Person> persons = personService.findAllPersons();
        if (persons.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") String id) {
        Person person = personService.findById(id);
        if (person == null) {
            return new ResponseEntity(new CustomErrorType("User with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @RequestMapping(value = "/person/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Person person, UriComponentsBuilder ucBuilder) {
        if (personService.isPersonExist(person)) {
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " +
                    person.getFirstName() + " already exist."),HttpStatus.CONFLICT);
        }
        personService.savePerson(person);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/person/{id}").buildAndExpand(person.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePerson(@PathVariable("id") String id, @RequestBody Person person) {
        Person currentPerson = personService.findById(id);

        if (currentPerson == null) {
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentPerson.setFirstName(person.getFirstName());
        currentPerson.setLastName(person.getLastName());

        personService.updatePerson(currentPerson);
        return new ResponseEntity<Person>(currentPerson, HttpStatus.OK);
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePerson(@PathVariable("id") String id) {
        Person user = personService.findById(id);
        if (user == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        personService.deletePersonById(id);
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/person", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deleteAllPersons() {
        personService.deleteAllPersons();
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }

}
