package com.example.demo.domain;

import java.time.LocalDate;

import javax.persistence.*;

import com.example.demo.converter.LocalDateConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "trashes")
public class Trash extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(nullable = false, name = "category_id", referencedColumnName = "id")
    private Category category;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate date;
    @Column(nullable = true)
    private Double capacity;
    @Column(nullable = false)
    private Double weight;
    @Column(nullable = true)
    private String memo;

    public Trash() {
    }

    public User getUser() {
        return this.user;
    }

    public Category getCategory() {
        return this.category;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Double getCapacity() {
        return this.capacity;
    }

    public Double getWeight() {
        return this.weight;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
