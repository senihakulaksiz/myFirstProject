package com.example.myFirstProject.controller;

import com.example.myFirstProject.dto.LoginModel;
import com.example.myFirstProject.model.Person;
import com.example.myFirstProject.repository.PersonRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginModel model) {
        Person person = _personRepository.findByName(model.getName());
        if (person == null) {
            return ResponseEntity.badRequest().body("Kullanıcı adı ya da şifre hatalı!");
        }
        boolean isPasswordMatch = BCrypt.checkpw(model.getPassword(), person.getPassword());
        if (!isPasswordMatch) {
            return ResponseEntity.badRequest().body("Kullanıcı adı ya da şifre hatalı!");
        }

        return ResponseEntity.ok().body("Giriş başarılı");
    }

    @PostMapping
    public Person insertPerson(@RequestBody Person person) {
        String hashedPwd = BCrypt.hashpw(person.getPassword(), BCrypt.gensalt());
        person.setPassword(hashedPwd);
        return _personRepository.save(person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id) {
        _personRepository.deleteById((long)id);
    }
}
