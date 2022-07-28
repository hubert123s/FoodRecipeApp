package com.example.foodrecipeapp.Meal;

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
