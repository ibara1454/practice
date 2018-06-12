package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findById(Integer id);
    List<Customer> findAll();
}
