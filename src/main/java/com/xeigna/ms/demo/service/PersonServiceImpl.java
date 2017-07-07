package com.xeigna.ms.demo.service;

import com.xeigna.ms.demo.model.Person;
import com.xeigna.ms.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("personService")
@Transactional
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository repository;

    @Override
    public Person findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    @Override
    public List<Person> findByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    @Override
    public List<Person> findAllPersons() {
        return repository.findAll();
    }

    @Override
    public boolean isPersonExist(Person person) {
        return findById(person.getId()) != null;
    }

    @Override
    public void savePerson(Person person) {
        repository.save(person);
    }

    @Override
    public void updatePerson(Person person) {
        savePerson(person);
    }

    @Override
    public void deletePersonById(String id) {
        repository.delete(id);
    }

    @Override
    public void deleteAllPersons() {
        repository.deleteAll();
    }
}
