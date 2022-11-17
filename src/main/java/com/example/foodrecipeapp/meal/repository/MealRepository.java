package com.example.foodrecipeapp.meal.repository;

import com.example.foodrecipeapp.meal.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {


    Optional<Meal> findByName(String name);

    @Query("SELECT coalesce(max(m.id), 0) FROM Meal m")
    Long getMaxId();
    boolean existsByName(String name);


}