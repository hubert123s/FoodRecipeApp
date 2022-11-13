package com.example.foodrecipeapp.Meal.dto;

import com.example.foodrecipeapp.Meal.model.TypeMeal;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealDto implements Serializable {
    private  Long id;
    private  String name;
    private  Integer preperationTime;
    private  String description;
    private TypeMeal typeMeal;
}
