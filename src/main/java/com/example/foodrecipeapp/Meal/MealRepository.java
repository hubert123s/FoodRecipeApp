package com.example.foodrecipeapp.Meal;

import com.example.foodrecipeapp.Meal.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {


    Optional<Meal> findAllByName(String name);
    List<Meal> findByIdGreaterThanAndIdLessThan(Long idmin,Long idmax);
    List<Meal> findAllByPreperationTimeOrderByPreperationTimeAsc(Integer min);

}