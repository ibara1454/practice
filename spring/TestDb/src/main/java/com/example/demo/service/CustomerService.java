package com.example.demo.service;

import java.util.Optional;

import java.util.*;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Returns customer with specified id, if it is exists.
     *
     * @param  id the id (also primary key) of customer
     * @return    specified customer
     * @exception NullPointerException
     */
    public Optional<Customer> findOne(Integer id) {
        Objects.requireNonNull(id);
        return customerRepository.findById(id);
    }

    /**
     * Returns all customers.
     *
     * @return    list of customer
     */
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
