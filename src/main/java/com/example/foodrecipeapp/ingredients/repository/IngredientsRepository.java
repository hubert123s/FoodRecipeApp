package com.example.foodrecipeapp.ingredients.repository;

import com.example.foodrecipeapp.ingredients.model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
}