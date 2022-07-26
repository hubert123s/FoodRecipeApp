package com.example.foodrecipeapp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/meal")
public class MealController {
    private  final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }
    @GetMapping("/{id}")
    ResponseEntity<MealDto> getMealById(@PathVariable Long id)
    {
        return mealService.getMealById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
