package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(nullable = false)
    public String name;

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
