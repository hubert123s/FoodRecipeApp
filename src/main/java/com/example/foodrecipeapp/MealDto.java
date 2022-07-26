package com.example.foodrecipeapp;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealDto implements Serializable {
    private  Long id;
    private  String name;
    private  Integer preperationTime;
    private  TypeMeal typeMeal;
}
