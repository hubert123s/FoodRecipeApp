package com.example.foodrecipeapp.meal.repository;

import com.example.foodrecipeapp.meal.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Optional<Meal> findByName(String name);
    @Query("SELECT coalesce(max(m.id), 0) FROM Meal m")
    Long getMaxId();
    boolean existsByName(String name);
    @Query("SELECT m FROM Meal m WHERE NOT EXISTS (SELECT 1 FROM Ingredient i WHERE i.meal = m AND i.name IN :ingredients)")
    List<Meal> findByIngredientsNotIn( @Param("ingredients") List<String> ingredients);
}
