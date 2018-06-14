package com.example.demo.controller;

import java.util.*;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * GET /customers/:id
     *
     * Returns customer with specified id.
     * If it is not exist, which may cause an exception
     *
     * @param  id the id (also primary key) of customer
     * @return    specified customer
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        return customerService.findOne(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * GET /customers/
     *
     * Returns all customers.
     *
     * @return List of customer
     */
    @GetMapping
    public List<Customer> showAll() {
        return customerService.findAll();
    }

    /**
     * POST /customers/
     *
     * @param customer specified customer
     * @return void
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Customer customer) {
        return customerService.save(customer)
            .map(c -> ResponseEntity.noContent().build())
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
