package com.example.myFirstProject.repository;

import com.example.myFirstProject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findByPerson_Id(Long personId);
    List<Order> findByOrderDateBetweenAndPerson_Id(LocalDateTime startDate, LocalDateTime endDate, Long personId);
}
