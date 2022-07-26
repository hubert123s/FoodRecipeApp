package com.example.foodrecipeapp;

import org.springframework.stereotype.Service;

@Service
public class MealDtoMapper {
    MealDto map(Meal meal)
    {
        MealDto dto= new MealDto();
        dto.setId(meal.getId());
        dto.setName(meal.getName());
        dto.setPreperationTime(meal.getPreperationTime());
        dto.setTypeMeal(meal.getTypeMeal());
        return dto;
    }


}
