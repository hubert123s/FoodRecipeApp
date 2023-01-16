package com.example.foodrecipeapp.meal.controller;

import com.example.foodrecipeapp.exception.DuplicatedMealException;
import com.example.foodrecipeapp.exception.NotFoundMealException;
import com.example.foodrecipeapp.image.service.MealImageService;
import com.example.foodrecipeapp.ingredient.dto.IngredientDto;
import com.example.foodrecipeapp.meal.dto.MealDto;
import com.example.foodrecipeapp.meal.model.Meal;
import com.example.foodrecipeapp.meal.repository.MealRepository;
import com.example.foodrecipeapp.meal.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meal")
class MealController {
    private final MealService mealService;
    private final MealImageService mealImageService;
    @GetMapping
    List<MealDto> getAllMeals(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                              @RequestParam(defaultValue = "2", required = false) int pageSize,
                              @RequestParam(defaultValue = "preparationTime", required = false) String sortBy,
                              @RequestParam(defaultValue = "ASC", required = false) String sortDirection) {
        return mealService.getAllMeals(pageNumber, pageSize, sortBy, sortDirection);
    }

    @GetMapping("/{id}")
    MealDto getMealById(@PathVariable Long id) throws NotFoundMealException {
        return mealService.getMealById(id);
    }

    @GetMapping("/{id}/ingredients")
    List<IngredientDto> getIngredientsByMealId(@PathVariable Long id) throws NotFoundMealException {
        return mealService.getIngredientsByMealId(id);
    }

    @PostMapping
    MealDto saveMeal(@RequestBody MealDto mealDto) throws DuplicatedMealException {
        return mealService.saveMeal(mealDto);
    }

    @PutMapping("/{id}")
    MealDto replaceMeal(@PathVariable Long id, @RequestBody MealDto mealDto) throws NotFoundMealException {
        return mealService.replaceMeal(id, mealDto);
    }

    @DeleteMapping("/{id}")
    void deleteMeal(@PathVariable Long id) throws NotFoundMealException {
        mealService.deleteMeal(id);
    }

    @GetMapping("/name")
    MealDto findByName(@RequestParam(value = "name") String name) throws NotFoundMealException {
        return mealService.findByName(name);
    }

    @GetMapping("/name/image")
    String findImageByName(@RequestParam(value = "name") Optional<String> name) throws NotFoundMealException {

        if (name.isPresent()) {
            return mealImageService.getImageURL(name.get());
        } else {
            throw new NotFoundMealException();
        }
    }

    @GetMapping("/ingredients")
    List<MealDto> findWithoutFewIngredients(@RequestParam(value = "without") String... ingredients) {
        return mealService.findWithOutFewIngredients(ingredients);
    }


}
