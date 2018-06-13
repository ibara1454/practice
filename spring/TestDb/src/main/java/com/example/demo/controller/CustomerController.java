package com.example.demo.controller;

import java.util.*;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * @exception Exception
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Customer> show(@PathVariable Integer id) {
        return customerService.findOne(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /customers/
     *
     * Returns all customers.
     */
    @GetMapping
    public List<Customer> showAll() {
        return customerService.findAll();
    }
}
