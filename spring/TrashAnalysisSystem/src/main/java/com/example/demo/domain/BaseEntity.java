package com.example.demo.domain;

import java.io.Serializable;

import javax.persistence.*;

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

    public void setId(Integer id) {
        this.id = id;
    }
}
