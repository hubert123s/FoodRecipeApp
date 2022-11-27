package com.example.foodrecipeapp.ingredient.mapper;


import com.example.foodrecipeapp.ingredient.dto.IngredientDto;
import com.example.foodrecipeapp.ingredient.model.Ingredient;
import com.example.foodrecipeapp.meal.repository.MealRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientDtoMapper {
    public IngredientDtoMapper(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    private final MealRepository mealRepository;

    public IngredientDto map(Ingredient ingredient) {
        IngredientDto dto = new IngredientDto();
        dto.setId(ingredient.getId());
        dto.setName(ingredient.getName());
        dto.setAmount(ingredient.getAmount());
        dto.setMealId(ingredient.getMeal().getId());
        return dto;
    }

    public Ingredient toEntity(IngredientDto ingredientDto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDto.getId());
        ingredient.setName(ingredientDto.getName());
        ingredient.setAmount(ingredientDto.getAmount());
        mealRepository.findById(ingredientDto.getMealId())
                .ifPresent(ingredient::setMeal);
        return ingredient;
    }
}
