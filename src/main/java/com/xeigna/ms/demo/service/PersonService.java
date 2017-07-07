package com.xeigna.ms.demo.service;

import com.xeigna.ms.demo.model.Person;

import java.util.List;

public interface PersonService {

    Person findById(String id);

    List<Person> findByLastName(String firstName);

    List<Person> findByFirstName(String firstName);

    List<Person> findAllPersons();

    public boolean isPersonExist(Person person);

    void savePerson(Person person);

    void updatePerson(Person person);

    void deletePersonById(String id);

    void deleteAllPersons();
}
