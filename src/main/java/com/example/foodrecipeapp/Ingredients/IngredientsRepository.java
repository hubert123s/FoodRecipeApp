package com.example.foodrecipeapp.Ingredients;

import com.example.foodrecipeapp.Ingredients.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
}