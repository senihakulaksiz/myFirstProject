package com.example.myFirstProject.controller;

import com.example.myFirstProject.model.Person;
import com.example.myFirstProject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonRepository _personRepository;

    public PersonController(PersonRepository personRepository) {
        this._personRepository = personRepository;
    }

    @GetMapping
    public List<Person> getPersons() {
        return _personRepository.findAll();
    }

    @PostMapping
    public Person insertPerson(@RequestBody Person person) {
        return _personRepository.save(person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id) {
        _personRepository.deleteById((long)id);
    }
}
