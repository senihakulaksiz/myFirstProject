package com.example.myFirstProject.service;

import com.example.myFirstProject.dto.LoginModelDTO;
import com.example.myFirstProject.dto.PersonDetailDTO;
import com.example.myFirstProject.dto.PersonSummaryDTO;
import com.example.myFirstProject.dto.RegisterModelDTO;
import com.example.myFirstProject.model.Person;

import java.util.List;

public interface PersonService {
    PersonDetailDTO createPerson(PersonDetailDTO personDetailDTO);
    PersonDetailDTO register(RegisterModelDTO registerModelDTO);
    PersonDetailDTO getPersonById(Long id);
    List<PersonSummaryDTO> getAllPersons();
    void deletePerson(Long id);
    Person login(LoginModelDTO loginModelDTO);
    List<PersonSummaryDTO> getPersonsByRole(String role);
}
