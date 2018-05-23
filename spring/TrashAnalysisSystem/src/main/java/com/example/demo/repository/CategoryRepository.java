package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.*;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
