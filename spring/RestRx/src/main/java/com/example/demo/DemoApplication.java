package com.example.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.reactivex.*;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

@Entity
@Table(name = "customers")
class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // Not allow empty string (0-length)
    @Column(nullable = false)
    @Size(min = 1)
    private String name;

    @Column
    private String foo;

    public Customer() {}

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("Customer(id='%d', name='%s')", this.id, this.name);
    }
}

interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findById(Integer id);
    List<Customer> findAll();
}

@Service
class DemoService {
    @Autowired
    CustomerRepository repository;

    public Observable<List<Customer>> findAll() {
        return Observable.just(repository.findAll());
    }
}

class TimeoutException extends RuntimeException {

}

@RequestMapping(path = "/api")
@RestController
class DemoController {
    @Autowired
    DemoService service;

    @GetMapping
    public Observable<List<Customer>> index() {
        return service.findAll()
            .timeout(1, TimeUnit.SECONDS)
            .retry(2)
            .onErrorResumeNext(Observable.error(new TimeoutException()));
    }
}

@ControllerAdvice
class ExceptionHandler {
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @org.springframework.web.bind.annotation.ExceptionHandler({ TimeoutException.class })
    @ResponseBody
    public Map<String, Object> handleError() {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("message", "Timeout");
        errorMap.put("status", HttpStatus.GATEWAY_TIMEOUT);
        return errorMap;
    }
}
