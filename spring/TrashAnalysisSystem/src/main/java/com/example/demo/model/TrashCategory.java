package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "trash_categories")
public class TrashCategory extends BaseEntity {
    @Column(nullable = false)
    private String name;

    public TrashCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
