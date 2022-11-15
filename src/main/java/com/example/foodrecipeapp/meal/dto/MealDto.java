package com.example.foodrecipeapp.meal.dto;

import com.example.foodrecipeapp.meal.model.TypeMeal;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealDto implements Serializable {
    private Long id;
    private String name;
    private Integer preperationTime;
    private String description;
    private TypeMeal typeMeal;
}
