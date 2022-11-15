package com.example.foodrecipeapp.ingredients.mapper;


import com.example.foodrecipeapp.ingredients.dto.IngredientsDto;
import com.example.foodrecipeapp.ingredients.model.Ingredients;
import com.example.foodrecipeapp.meal.repository.MealRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientsDtoMapper {
    public IngredientsDtoMapper(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    private final MealRepository mealRepository;

    public IngredientsDto map(Ingredients ingredients) {
        IngredientsDto dto = new IngredientsDto();
        dto.setId(ingredients.getId());
        dto.setName(ingredients.getName());
        dto.setAmount(ingredients.getAmount());
        dto.setMealId(ingredients.getMeal().getId());
        return dto;
    }

    public Ingredients toEntity(IngredientsDto ingredientsDto) {
        Ingredients ingredients = new Ingredients();
        ingredients.setId(ingredientsDto.getId());
        ingredients.setName(ingredientsDto.getName());
        ingredients.setAmount(ingredientsDto.getAmount());
        mealRepository.findById(ingredientsDto.getMealId())
                .ifPresent(ingredients::setMeal);
        return ingredients;
    }
}
