package com.example.foodrecipeapp.Meal;

import com.example.foodrecipeapp.Ingredients.IngredientsDtoMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class MealService {
    private  final MealRepository mealRepository;
    private  final MealDtoMapper mealDtoMapper;
    private final IngredientsDtoMapper ingredientsDtoMapper;


    public MealService(MealRepository mealRepository, MealDtoMapper mealDtoMapper, IngredientsDtoMapper ingredientsDtoMapper) {
        this.mealRepository = mealRepository;
        this.mealDtoMapper = mealDtoMapper;
        this.ingredientsDtoMapper = ingredientsDtoMapper;
    }

    Optional<MealDto> getMealById(Long id)
    {
        return mealRepository.findById(id)
                .map(MealDtoMapper::toDto);
    }
    List <MealDto> getAllMeals()
    {
        return mealRepository.findAll()
                .stream().map(MealDtoMapper::toDto)
                .collect(Collectors.toList());
    }
    List<MealIngredientsDto> getIngredientsByMealId(Long mealId)
    {
        return mealRepository.findById(mealId)
                .map(Meal::getIngredientsList)
                .orElse(Collections.emptyList())
                .stream()
                .map(MealIngredientsDtoMapper::map)
                .toList();
    }
    MealDto saveMeal(MealDto mealDto)
    {
        Meal meal = mealDtoMapper.toEntity(mealDto);
        Meal savedMeal = mealRepository.save(meal);
        return MealDtoMapper.toDto(savedMeal);
    }
    Optional <MealDto> replaceMeal(Long mealId,MealDto mealDto)
    {
        if(!mealRepository.existsById(mealId))
        {
            return Optional.empty();
        }
        mealDto.setId(mealId);
        Meal mealToUpdate = mealDtoMapper.toEntity(mealDto);
        Meal updateEntity =mealRepository.save(mealToUpdate);
        return Optional.of(MealDtoMapper.toDto(updateEntity));
    }


    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

    List<Meal> findByName(String name) {
       return mealRepository.findAllByName(name);
    }

}
