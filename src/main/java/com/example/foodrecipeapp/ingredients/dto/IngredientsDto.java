package com.example.foodrecipeapp.ingredients.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsDto implements Serializable {
    private Long id;
    private String name;
    private Integer amount;
    private Long mealId;
}
