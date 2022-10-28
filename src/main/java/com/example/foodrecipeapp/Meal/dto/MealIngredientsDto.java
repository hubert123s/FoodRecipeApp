package com.example.foodrecipeapp.Meal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealIngredientsDto {
    private  Long id;
    private  String name;
    private Integer amount;

    public String emailFormat()
    {
        return " name='" + name + '\'' +
                ", amount=" + amount ;
    }

}
