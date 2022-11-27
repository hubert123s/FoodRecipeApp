package com.example.foodrecipeapp.ingredient.repository;

import com.example.foodrecipeapp.ingredient.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}