package com.example.foodrecipeapp.Meal.mapper;

import com.example.foodrecipeapp.Ingredients.model.Ingredients;
import com.example.foodrecipeapp.Meal.dto.MealIngredientsDto;
import org.springframework.stereotype.Service;

@Service
public class MealIngredientsDtoMapper {

    public static MealIngredientsDto map(Ingredients ingredients)
    {
        MealIngredientsDto dto = new MealIngredientsDto();
        dto.setId(ingredients.getId());
        dto.setName(ingredients.getName());
        dto.setAmount(ingredients.getAmount());
        return dto;
    }
}
