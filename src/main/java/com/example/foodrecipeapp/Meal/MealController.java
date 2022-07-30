package com.example.foodrecipeapp.Meal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/meal")
public class MealController {
    private  final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
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
    ResponseEntity<?> replaceMeal(@PathVariable Long id, @RequestBody MealDto mealDto)
    {
        return mealService.replaceMeal(id,mealDto)
                .map(m->ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping ("/{id}")
    ResponseEntity<?> deleteMeal (@PathVariable Long id)
    {
        mealService.deleteMeal(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/name")
    @ResponseBody
    List<MealDto> findBy(@RequestParam(value = "name") String name)
    {
        return mealService.findByName(name);
    }


}
