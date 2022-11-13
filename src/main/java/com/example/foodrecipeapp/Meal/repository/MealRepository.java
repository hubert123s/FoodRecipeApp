package com.example.foodrecipeapp.Meal.repository;

import com.example.foodrecipeapp.Meal.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {


    Optional<Meal> findAllByName(String name);
    @Query("SELECT coalesce(max(m.id), 0) FROM Meal m")
    Long getMaxId();
    @Query("SELECT m FROM Meal m ORDER BY m.typeMeal ASC")
    List<Meal> sortByStatus();
    @Query(value = "select name from Meal order by type_meal ASC ",
    nativeQuery = true)
    List<String> findAllNameandSortByStatus();


}