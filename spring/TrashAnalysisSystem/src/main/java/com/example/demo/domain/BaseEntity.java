package com.example.demo.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class BaseEntity implements Serializable {
    protected static final long serialVersionUID = 1L;

    // @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    public Integer getId() {
        return this.id;
    }
}
