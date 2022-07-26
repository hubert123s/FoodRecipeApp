package com.example.foodrecipeapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}