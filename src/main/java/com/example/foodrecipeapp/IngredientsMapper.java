package com.example.foodrecipeapp;


import org.springframework.stereotype.Service;

@Service
public class IngredientsMapper {
    IngredientsDto map(Ingredients ingredients)
    {
        IngredientsDto dto =new IngredientsDto();
        dto.setId(ingredients.getId());
        dto.setName(ingredients.getName());
        dto.setAmount(ingredients.getAmount());
        return  dto;
    }
}
