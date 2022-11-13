package com.example.foodrecipeapp.Ingredients.repository;

import com.example.foodrecipeapp.Ingredients.model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
}