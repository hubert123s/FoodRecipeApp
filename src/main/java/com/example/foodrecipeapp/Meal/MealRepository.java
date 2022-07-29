package com.example.foodrecipeapp.Meal;

import com.example.foodrecipeapp.Meal.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {


    List<Meal> findAllByName(String name);
    List<Meal> findByIdGreaterThanAndIdLessThan(Long idmin,Long idmax);
    List<Meal> findAllByPreperationTimeOrderByPreperationTimeAsc(Integer min);

}