package com.example.foodrecipeapp.ingredient.service;

import com.example.foodrecipeapp.exception.NotFoundIngredientException;
import com.example.foodrecipeapp.ingredient.model.Ingredient;
import com.example.foodrecipeapp.ingredient.repository.IngredientRepository;
import com.example.foodrecipeapp.ingredient.dto.IngredientDto;
import com.example.foodrecipeapp.ingredient.mapper.IngredientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientDtoMapper ingredientDtoMapper;



    public IngredientDto getIngredientsById(Long id) throws NotFoundIngredientException {
        return ingredientRepository.findById(id)
                .map(ingredientDtoMapper::map)
                .orElseThrow(()->new NotFoundIngredientException());
    }

    public IngredientDto saveIngredients(IngredientDto ingredientDto) {
        Ingredient ingredientToSave = ingredientDtoMapper.toEntity(ingredientDto);
        Ingredient savedIngredient = ingredientRepository.save(ingredientToSave);
        return ingredientDtoMapper.map(savedIngredient);
    }

    public void deleteIngredients(Long id) {
        ingredientRepository.deleteById(id);
    }
}
