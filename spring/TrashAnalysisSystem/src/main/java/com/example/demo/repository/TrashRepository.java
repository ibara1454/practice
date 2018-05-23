package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.demo.domain.*;

public interface TrashRepository extends JpaRepository<Trash, Integer> {
    Optional<Trash> findById(Integer id);
    List<Trash> findAll();

    List<Trash> findByCategoryId(Integer categoryId);
    List<Trash> findByDate(LocalDate date);
    List<Trash> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Trash> findByDateBetweenAndCategoryId(LocalDate start, LocalDate end, Integer categoryId);
    List<Trash> findByUser(User user);
    List<Trash> findByCategoryIdAndUserId(Integer categoryId, Integer userId);
    List<Trash> findByDateAndUserId(LocalDate date, Integer userId);
    List<Trash> findByDateBetweenAndUserId(LocalDate startDate, LocalDate endDate, Integer userId);
    List<Trash> findByDateBetweenAndCategoryIdAndUserId(LocalDate startDate, LocalDate endDate, Integer category, Integer userId);
}
