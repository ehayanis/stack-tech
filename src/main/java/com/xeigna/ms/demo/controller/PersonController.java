package com.xeigna.ms.demo.controller;

import com.xeigna.ms.demo.model.Person;
import com.xeigna.ms.demo.service.PersonService;
import com.xeigna.ms.demo.util.CustomErrorType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class PersonController {

    // public static final Logger logger = LoggerFactory.getLogger();

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/person/", method = RequestMethod.GET)
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

}
