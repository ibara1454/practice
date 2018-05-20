package com.example.tas.app.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mail_address", nullable = false)
    private String mailAddress;

    @Column(nullable = false)
    private String password;

    @Override
    public String toString() {
        return String.format(
            "User(id=%d, mailAddress='%s', password='%s')",
            id, mailAddress, password
        );
    }
}
