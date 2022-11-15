package com.example.foodrecipeapp.meal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealIngredientsDto {
    private Long id;
    private String name;
    private Integer amount;

    public String emailFormat() {
        return " name='" + name + '\'' +
                ", amount=" + amount;
    }

}
