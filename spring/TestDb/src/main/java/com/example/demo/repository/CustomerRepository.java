package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    /**
     * @param id
     * @return
     */
    Optional<Customer> findById(Integer id);

    /**
     * Saves the given {@link Customer}
     *
     * @param customer
     * @return
     */
    <T extends Customer> T save(T customer);

    /**
     * Save customer by using a safe way
     *
     * @param customer
     * @return optional of customer
     */
    default Optional<Customer> safeSave(Customer customer) {
        try {
            return Optional.of(save(customer));
        } catch(Exception ex) {
            return Optional.empty();
        }
    }

    List<Customer> findAll();
}
