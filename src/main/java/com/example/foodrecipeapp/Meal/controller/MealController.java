package com.example.foodrecipeapp.Meal.controller;

import com.example.foodrecipeapp.Exceptions.NotFoundMealException;
import com.example.foodrecipeapp.Image.service.MealImageService;
import com.example.foodrecipeapp.Meal.service.MealService;
import com.example.foodrecipeapp.Meal.dto.MealDto;
import com.example.foodrecipeapp.Meal.dto.MealIngredientsDto;
import com.example.foodrecipeapp.Meal.model.Meal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/meal")
class MealController {
    private  final MealService mealService;
    private final MealImageService mealImageService;

    public MealController(MealService mealService, MealImageService mealImageService) {
        this.mealService = mealService;
        this.mealImageService = mealImageService;
    }
    @GetMapping
    List<MealDto> getAllMeals(@RequestParam(defaultValue = "0",required = false) int pageNumber,
                              @RequestParam(defaultValue = "2",required = false) int pageSize,
                              @RequestParam(defaultValue = "preperationTime",required = false) String sortBy,
                              @RequestParam(defaultValue = "ascending",required = false) String sortDirection)
    {
        return mealService.getAllMeals(pageNumber,pageSize,sortBy,sortDirection);
    }
    @GetMapping("/{id}")
    ResponseEntity<MealDto> getMealById(@PathVariable Long id)
    {
        return mealService.getMealById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{id}/ingredients")
    ResponseEntity<List<MealIngredientsDto>> getIngredientsByMealId(@PathVariable Long id)
    {
        if(mealService.getMealById(id).isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mealService.getIngredientsByMealId(id));
    }
    @PostMapping
    ResponseEntity<MealDto> saveMeal(@RequestBody MealDto mealDto)
    {
        MealDto savedMeal = mealService.saveMeal(mealDto);
        URI savedMealUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMeal.getId())
                .toUri();
        return ResponseEntity.created(savedMealUri).body(savedMeal);
    }
    @PutMapping("/{id}")
    ResponseEntity<MealDto> replaceMeal(@PathVariable Long id, @RequestBody MealDto mealDto)
    {
        return ResponseEntity.ok(mealService.replaceMeal(id,mealDto).get());
    }
    @DeleteMapping ("/{id}")
    ResponseEntity<?> deleteMeal (@PathVariable Long id)
    {
        mealService.deleteMeal(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/name")
    @ResponseBody
    List<MealDto> findByName(@RequestParam(value = "name") String name)
    {
        return mealService.findByName(name);
    }
    @GetMapping("/name/image")
    //@ResponseBody
    public String findImageByName(@RequestParam(value = "name") Optional<String> name)
    {

         if(name.isPresent()) {
             return mealImageService.getImageURL(name.get());
         }else {
             throw new NotFoundMealException();
         }
    }
    @GetMapping("/ingredient")
    List<Meal> findByWithOutThisIngredient(@RequestParam(value = "without") String ingredient)
    {
        return mealService.findWithOutThisIngredient(ingredient);
    }
    @GetMapping("/ingredients")
    String findWithOutFewIngredients(@RequestParam(value = "without") String ... ingredients)
    {
        return mealService.findWithOutFewIngredients(ingredients);
    }



}
