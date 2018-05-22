package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trashes")
public class Trash extends BaseEntity {
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(nullable = false, name = "category_id")
    private TrashCategory category;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = true)
    private Double capacity;
    @Column(nullable = false)
    private Double weight;
    @Column(nullable = true)
    private String memo;

    public Trash() {

    }
}
