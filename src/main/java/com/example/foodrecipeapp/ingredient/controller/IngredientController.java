package com.example.foodrecipeapp.ingredient.controller;

import com.example.foodrecipeapp.exception.NotFoundIngredientException;
import com.example.foodrecipeapp.ingredient.dto.IngredientDto;
import com.example.foodrecipeapp.ingredient.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;
    @GetMapping("/{id}")
    IngredientDto getIngredientById(@PathVariable Long id) throws NotFoundIngredientException {
        return ingredientService.getIngredientsById(id);
    }

    @PostMapping
    IngredientDto saveIngredient(@RequestBody IngredientDto ingredientDto) {
        return ingredientService.saveIngredients(ingredientDto);
    }

    @DeleteMapping("/{id}")
    void deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredients(id);
    }
}
