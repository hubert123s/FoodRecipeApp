package com.example.foodrecipeapp.Ingredients;


import com.example.foodrecipeapp.Meal.MealRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientsDtoMapper {
    public IngredientsDtoMapper(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    private final MealRepository mealRepository;
      IngredientsDto map(Ingredients ingredients)
    {
        IngredientsDto dto =new IngredientsDto();
        dto.setId(ingredients.getId());
        dto.setName(ingredients.getName());
        dto.setAmount(ingredients.getAmount());
        dto.setMealId(ingredients.getMeal().getId());
        return  dto;
    }
    Ingredients toEntity( IngredientsDto ingredientsDto)
    {
        Ingredients ingredients = new Ingredients();
        ingredients.setId(ingredientsDto.getId());
        ingredients.setName(ingredientsDto.getName());
        ingredients.setAmount(ingredientsDto.getAmount());
        mealRepository.findById(ingredientsDto.getMealId())
                .ifPresent(ingredients::setMeal);
        return  ingredients;
    }
}
