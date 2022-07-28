package com.example.foodrecipeapp.Ingredients;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {
    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }
    @GetMapping("/{id}")
    ResponseEntity<IngredientsDto> getIngredientsById(@PathVariable Long id)
    {
        return ingredientsService.getIngredientsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    ResponseEntity<IngredientsDto> saveIngredients(@RequestBody IngredientsDto ingredientsDto)
    {
        IngredientsDto savedIngredients =ingredientsService.saveIngredients(ingredientsDto);
        URI savedIngredientsUri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedIngredients.getId())
                .toUri();
        return ResponseEntity.created(savedIngredientsUri).body(savedIngredients);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteIngredients(@PathVariable Long id)
    {
        ingredientsService.deleteIngredients(id);
        return ResponseEntity.noContent().build();
    }
}
