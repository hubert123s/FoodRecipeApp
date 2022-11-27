package com.example.foodrecipeapp.newsletter;

import com.example.foodrecipeapp.ingredient.model.Ingredient;
import com.example.foodrecipeapp.meal.model.Meal;
import com.example.foodrecipeapp.meal.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ContentGenerator {
    private final MealRepository mealRepository;
    public String recipeToEmailTemplate(Long id) {
        List<String> ingredients = mealRepository
                .findById(id)
                .stream()
                .map(Meal::getIngredientList)
                .flatMap(Collection::stream)
                .map(Ingredient::emailFormat)
                .toList();
        String meal = mealRepository
                .findById(id)
                .orElseThrow()
                .emailFormat();
        return meal + ingredients;
    }
}
