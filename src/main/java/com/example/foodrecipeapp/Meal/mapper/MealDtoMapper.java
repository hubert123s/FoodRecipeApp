package com.example.foodrecipeapp.Meal.mapper;

import com.example.foodrecipeapp.Meal.dto.MealDto;
import com.example.foodrecipeapp.Meal.model.Meal;
import org.springframework.stereotype.Service;

@Service
public class MealDtoMapper {
    public static MealDto toDto(Meal meal)
    {
        MealDto dto= new MealDto();
        dto.setId(meal.getId());
        dto.setName(meal.getName());
        dto.setPreperationTime(meal.getPreperationTime());
        dto.setDescription(meal.getDescription());
        dto.setTypeMeal(meal.getTypeMeal());
        return dto;
    }
    public Meal toEntity(MealDto mealDto)
    {
        Meal meal = new Meal();
        meal.setId(mealDto.getId());
        meal.setName(mealDto.getName());
        meal.setPreperationTime(mealDto.getPreperationTime());
        meal.setDescription(mealDto.getDescription());
        meal.setTypeMeal(mealDto.getTypeMeal());
        return meal;
    }

}
