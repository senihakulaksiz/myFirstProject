package com.example.myFirstProject.service;

import com.example.myFirstProject.dto.LoginModelDTO;
import com.example.myFirstProject.dto.PersonDetailDTO;
import com.example.myFirstProject.dto.PersonSummaryDTO;
import com.example.myFirstProject.exception.ResourceNotFoundException;
import com.example.myFirstProject.model.Person;
import com.example.myFirstProject.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PersonDetailDTO createPerson(PersonDetailDTO personDetailDTO) {
        String role = personDetailDTO.getRole();
        if (!role.equals("business") && !role.equals("customer")) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }

        Person person = modelMapper.map(personDetailDTO, Person.class);
        person.setPassword(BCrypt.hashpw(person.getPassword(), BCrypt.gensalt()));
        Person savedPerson = personRepository.save(person);
        return modelMapper.map(savedPerson, PersonDetailDTO.class);
    }


    @Override
    public PersonDetailDTO getPersonById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        return modelMapper.map(person, PersonDetailDTO.class);
    }

    @Override
    public List<PersonSummaryDTO> getAllPersons() {
        return personRepository.findAll().stream()
                .map(person -> modelMapper.map(person, PersonSummaryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person login(LoginModelDTO loginModelDTO) {
        Person person = personRepository.findByName(loginModelDTO.getName());
        if (person != null && BCrypt.checkpw(loginModelDTO.getPassword(), person.getPassword())) {
            return person;
        }
        return null;
    }

    @Override
    public List<PersonSummaryDTO> getPersonsByRole(String role) {
        return personRepository.findByRole(role).stream()
                .map(person -> modelMapper.map(person, PersonSummaryDTO.class))
                .collect(Collectors.toList());
    }


}
