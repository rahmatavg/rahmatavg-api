package com.rahmatavg.api.example.controllers;

import com.rahmatavg.api.example.model.Person;
import com.rahmatavg.api.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonControllers {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/get_all")
    public ResponseEntity<List<Person>> getAllPerson() {
        try {
            List<Person> persons = new ArrayList<Person>();
            personRepository.findAll().forEach(persons::add);

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(persons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {
        Person person = personRepository.findById(id);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/add_person")
    public ResponseEntity<String> createTutorial(@RequestBody Person person) {
        try {
            personRepository.save(new Person(null, person.getName(), person.getAge(), person.getBirthday(), person.getMoney(), person.getDeleted()));
            return new ResponseEntity<>("Tutorial was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("ERROR "+e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete_person/{id}")
    public ResponseEntity<String> deleteTutorial(@PathVariable("id") long id) {
        try {
            int result = personRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Tutorial with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Tutorial was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete tutorial.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update_person/{id}")
    public ResponseEntity<String> updateTutorial(@PathVariable("id") long id, @RequestBody Person person) {
        try {
            personRepository.update(person);
            return new ResponseEntity<>("Tutorial was updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
