package com.example.foodrecipeapp.ingredient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto implements Serializable {
    private Long id;
    private String name;
    private Integer amount;
    private Long mealId;
}
