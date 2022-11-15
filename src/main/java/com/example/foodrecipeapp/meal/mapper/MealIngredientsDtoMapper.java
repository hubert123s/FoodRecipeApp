package com.example.foodrecipeapp.meal.mapper;

import com.example.foodrecipeapp.ingredients.model.Ingredients;
import com.example.foodrecipeapp.meal.dto.MealIngredientsDto;
import org.springframework.stereotype.Service;

@Service
public class MealIngredientsDtoMapper {

    public static MealIngredientsDto map(Ingredients ingredients) {
        MealIngredientsDto dto = new MealIngredientsDto();
        dto.setId(ingredients.getId());
        dto.setName(ingredients.getName());
        dto.setAmount(ingredients.getAmount());
        return dto;
    }
}
