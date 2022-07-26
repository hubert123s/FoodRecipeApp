package com.example.foodrecipeapp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
}