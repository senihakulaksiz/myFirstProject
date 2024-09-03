package com.example.myFirstProject.repository;

import com.example.myFirstProject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByName(String name);
    List<Person> findByRole(String role);
}
