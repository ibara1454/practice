package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity implements Serializable {
    protected static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    public Integer getId() {
        return this.id;
    }
}
