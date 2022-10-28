package com.example.foodrecipeapp.Meal;

import com.example.foodrecipeapp.Ingredients.Ingredients;
import com.example.foodrecipeapp.Meal.dto.MealIngredientsDto;
import org.springframework.stereotype.Service;

@Service
class MealIngredientsDtoMapper {

    static MealIngredientsDto map(Ingredients ingredients)
    {
        MealIngredientsDto dto = new MealIngredientsDto();
        dto.setId(ingredients.getId());
        dto.setName(ingredients.getName());
        dto.setAmount(ingredients.getAmount());
        return dto;
    }
}
