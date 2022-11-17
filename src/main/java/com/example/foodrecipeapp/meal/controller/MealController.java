package com.example.foodrecipeapp.meal.controller;

import com.example.foodrecipeapp.exception.DuplicatedMealException;
import com.example.foodrecipeapp.exception.NotFoundMealException;
import com.example.foodrecipeapp.image.service.MealImageService;
import com.example.foodrecipeapp.ingredients.dto.IngredientsDto;
import com.example.foodrecipeapp.meal.dto.MealDto;
import com.example.foodrecipeapp.meal.model.Meal;
import com.example.foodrecipeapp.meal.service.MealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/meal")
class MealController {
    private final MealService mealService;
    private final MealImageService mealImageService;

    public MealController(MealService mealService, MealImageService mealImageService) {
        this.mealService = mealService;
        this.mealImageService = mealImageService;
    }

    @GetMapping
    ResponseEntity<List<MealDto>> getAllMeals(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                              @RequestParam(defaultValue = "2", required = false) int pageSize,
                              @RequestParam(defaultValue = "preperationTime", required = false) String sortBy,
                              @RequestParam(defaultValue = "ascending", required = false) String sortDirection) {
        return ResponseEntity.ok(mealService.getAllMeals(pageNumber, pageSize, sortBy, sortDirection));
    }

    @GetMapping("/{id}")
    ResponseEntity<MealDto> getMealById(@PathVariable Long id) throws NotFoundMealException {
        return ResponseEntity.ok(mealService.getMealById(id));
    }

    @GetMapping("/{id}/ingredients")
    ResponseEntity<List<IngredientsDto>> getIngredientsByMealId(@PathVariable Long id) throws NotFoundMealException {
        return ResponseEntity.ok(mealService.getIngredientsByMealId(id));
    }

    @PostMapping
    ResponseEntity<MealDto> saveMeal(@RequestBody MealDto mealDto) throws DuplicatedMealException {
        MealDto savedMeal = mealService.saveMeal(mealDto);
        URI savedMealUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMeal.getId())
                .toUri();
        return ResponseEntity.created(savedMealUri).body(savedMeal);
    }

    @PutMapping("/{id}")
    ResponseEntity<MealDto> replaceMeal(@PathVariable Long id, @RequestBody MealDto mealDto) throws NotFoundMealException {
        return ResponseEntity.ok(mealService.replaceMeal(id, mealDto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteMeal(@PathVariable Long id) throws NotFoundMealException {
        mealService.deleteMeal(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/name")
    ResponseEntity<MealDto> findByName(@RequestParam(value = "name") String name) throws NotFoundMealException {
        return ResponseEntity.ok(mealService.findByName(name));
    }

    @GetMapping("/name/image")
    public ResponseEntity<String> findImageByName(@RequestParam(value = "name") Optional<String> name) throws NotFoundMealException {

        if (name.isPresent()) {
            return ResponseEntity.ok(mealImageService.getImageURL(name.get()));
        } else {
            throw new NotFoundMealException();
        }
    }

    @GetMapping("/ingredient")
    ResponseEntity<List<Meal>> findByWithOutThisIngredient(@RequestParam(value = "without") String ingredient) {
        return ResponseEntity.ok(mealService.findWithOutThisIngredient(ingredient));
    }

    @GetMapping("/ingredients")
    ResponseEntity<List<MealDto>> findWithOutFewIngredients(@RequestParam(value = "without") String... ingredients) {
        return ResponseEntity.ok(mealService.findWithOutFewIngredients(ingredients));
    }


}
