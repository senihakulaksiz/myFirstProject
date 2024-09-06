package com.example.myFirstProject.controller;

import com.example.myFirstProject.dto.LoginModelDTO;
import com.example.myFirstProject.dto.PersonDetailDTO;
import com.example.myFirstProject.dto.PersonSummaryDTO;
import com.example.myFirstProject.dto.RegisterModelDTO;
import com.example.myFirstProject.model.Person;
import com.example.myFirstProject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonSummaryDTO> getPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/role/{role}")
    public List<PersonSummaryDTO> getPersonsByRole(@PathVariable String role) {
        return personService.getPersonsByRole(role);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginModelDTO model) {
        Person person = personService.login(model);
        if (person == null) {
            return ResponseEntity.badRequest().body("Name or password is incorrect!");
        }
        return ResponseEntity.ok().body("Login successful");
    }

    @PostMapping("/register")
    public PersonDetailDTO register(@RequestBody RegisterModelDTO registerModelDTO) {
        return personService.register(registerModelDTO);
    }

    @PostMapping
    public PersonDetailDTO insertPerson(@RequestBody PersonDetailDTO personDetailDTO) {
        return personService.createPerson(personDetailDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
