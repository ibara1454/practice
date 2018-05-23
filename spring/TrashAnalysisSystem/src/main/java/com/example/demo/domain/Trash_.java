package com.example.demo.domain;

import java.time.LocalDate;

import javax.persistence.metamodel.*;

/**
 * Metaclass of Trash, which is used by generating query string
 * by Criteria API.
 */
@StaticMetamodel(Trash.class)
public class Trash_ {
    // TODO: refactoring `user`, `category` with `userId`, `categoryId`,
    //     so we can build the criteria query without User and Category instance
    public static volatile SingularAttribute<Trash, Integer> id;
    public static volatile SingularAttribute<Trash, User> user;
    public static volatile SingularAttribute<Trash, Category> category;
    public static volatile SingularAttribute<Trash, LocalDate> date;
    public static volatile SingularAttribute<Trash, Double> capacity;
    public static volatile SingularAttribute<Trash, Double> weight;
    public static volatile SingularAttribute<Trash, String> memo;
}
