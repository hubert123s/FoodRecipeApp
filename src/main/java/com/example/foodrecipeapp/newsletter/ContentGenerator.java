package com.example.foodrecipeapp.newsletter;

import com.example.foodrecipeapp.ingredients.model.Ingredients;
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
                .map(Meal::getIngredientsList)
                .flatMap(Collection::stream)
                .map(Ingredients::emailFormat)
                .toList();
        String meal = mealRepository
                .findById(id)
                .orElseThrow()
                .emailFormat();
        return meal + ingredients;
    }
}
