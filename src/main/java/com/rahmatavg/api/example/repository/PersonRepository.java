package com.rahmatavg.api.example.repository;

import com.rahmatavg.api.example.model.Person;

import java.util.List;

public interface PersonRepository {
    int save(Person person);
    int update(Person person);
    Person findById(Long id);
    int deleteById(Long id);
    List<Person> findAll();
}
