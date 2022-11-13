package com.example.foodrecipeapp.Ingredients.service;

import com.example.foodrecipeapp.Ingredients.repository.IngredientsRepository;
import com.example.foodrecipeapp.Ingredients.dto.IngredientsDto;
import com.example.foodrecipeapp.Ingredients.mapper.IngredientsDtoMapper;
import com.example.foodrecipeapp.Ingredients.model.Ingredients;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientsService {
    private final IngredientsRepository ingredientsRepository;
    private final IngredientsDtoMapper ingredientsDtoMapper;

    public IngredientsService(IngredientsRepository ingredientsRepository, IngredientsDtoMapper ingredientsDtoMapper) {
        this.ingredientsRepository = ingredientsRepository;
        this.ingredientsDtoMapper = ingredientsDtoMapper;
    }
    public Optional<IngredientsDto> getIngredientsById(Long id)
    {
        return ingredientsRepository.findById(id)
                .map(ingredientsDtoMapper::map);
    }
    public IngredientsDto saveIngredients( IngredientsDto ingredientsDto)
    {
        Ingredients ingredientsToSave = ingredientsDtoMapper.toEntity(ingredientsDto);
        Ingredients savedIngredients = ingredientsRepository.save(ingredientsToSave);
        return ingredientsDtoMapper.map(savedIngredients);
    }

    public void deleteIngredients(Long id)
    {
        ingredientsRepository.deleteById(id);
    }
}
