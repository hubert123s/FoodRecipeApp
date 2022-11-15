package com.example.foodrecipeapp.meal.service;

import com.example.foodrecipeapp.ingredients.model.Ingredients;
import com.example.foodrecipeapp.ingredients.mapper.IngredientsDtoMapper;
import com.example.foodrecipeapp.exceptions.DuplicatedMealException;
import com.example.foodrecipeapp.exceptions.NotFoundMealException;
import com.example.foodrecipeapp.meal.mapper.MealDtoMapper;
import com.example.foodrecipeapp.meal.mapper.MealIngredientsDtoMapper;
import com.example.foodrecipeapp.meal.repository.MealRepository;
import com.example.foodrecipeapp.meal.dto.MealDto;
import com.example.foodrecipeapp.meal.dto.MealIngredientsDto;
import com.example.foodrecipeapp.meal.model.Meal;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class MealService {
    private final MealRepository mealRepository;
    private final MealDtoMapper mealDtoMapper;
    private final IngredientsDtoMapper ingredientsDtoMapper;


    public MealService(MealRepository mealRepository, MealDtoMapper mealDtoMapper, IngredientsDtoMapper ingredientsDtoMapper) {
        this.mealRepository = mealRepository;
        this.mealDtoMapper = mealDtoMapper;
        this.ingredientsDtoMapper = ingredientsDtoMapper;
    }

    public Optional<MealDto> getMealById(Long id) throws NotFoundMealException {
        if (!mealRepository.existsById(id)) {
            throw new NotFoundMealException();
        }
        return mealRepository.findById(id)
                .map(MealDtoMapper::toDto);
    }

    public List<MealDto> getAllMeals(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        if (sortDirection.equalsIgnoreCase("ascending")) {
            return mealRepository.findAll
                            (PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending()))
                    .stream()
                    .map(MealDtoMapper::toDto)
                    .toList();
        } else {
            return mealRepository.findAll
                            (PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending()))
                    .stream()
                    .map(MealDtoMapper::toDto)
                    .toList();
        }
    }

    public List<MealIngredientsDto> getIngredientsByMealId(Long mealId) throws NotFoundMealException {
        return mealRepository.findById(mealId)
                .map(Meal::getIngredientsList)
                .orElseThrow(NotFoundMealException::new)
                .stream()
                .map(MealIngredientsDtoMapper::map)
                .toList();
    }

    public MealDto saveMeal(MealDto mealDto) throws DuplicatedMealException {
        //Optional<Meal> optionalMealDto = mealRepository.findAllByName(mealDto.getName());
        if (mealRepository.existsByName(mealDto.getName())) {
            throw new DuplicatedMealException(mealDto.getName());
        }
        //optionalMealDto.orElseThrow(()-> new DuplicateFormatFlagsException(""));
        Meal meal = mealDtoMapper.toEntity(mealDto);
        Meal savedMeal = mealRepository.save(meal);
        return MealDtoMapper.toDto(savedMeal);
    }

    public Optional<MealDto> replaceMeal(Long mealId, MealDto mealDto) {
        if (!mealRepository.existsById(mealId)) {
            return Optional.empty();
        }
        mealDto.setId(mealId);
        Meal mealToUpdate = mealDtoMapper.toEntity(mealDto);
        Meal updateEntity = mealRepository.save(mealToUpdate);
        return Optional.of(MealDtoMapper.toDto(updateEntity));
    }

    public void deleteMeal(Long id) throws NotFoundMealException {
        if (!mealRepository.existsById(id)) {
            throw new NotFoundMealException();
        }
        mealRepository.deleteById(id);
    }
    public MealDto findByName(String name) throws NotFoundMealException {

        Optional<Meal> mealByName = mealRepository.findAllByName(name);
        if (mealByName.isEmpty()) {
            throw new NotFoundMealException();
        } else {
            return mealByName.stream()
                    .map(MealDtoMapper::toDto)
                    .findFirst()
                    .get();
        }
    }


    public String findWith(String ingredient) {
        return mealRepository.findAll()
                .stream()
                .map(Meal::getIngredientsList)
                .flatMap(Collection::stream)
                .map(Ingredients::getName)
                .toList().toString();
    }

    public List<Meal> findWithOutThisIngredient(String ingredient) {
        return mealRepository.findAll()
                .stream()
                .filter(meal -> meal.getIngredientsList()
                        .stream().noneMatch(ingredients -> ingredients
                                .getName()
                                .equalsIgnoreCase(ingredient)))
                .toList();
    }

    public String findWithOutFewIngredients(String... ingredients) {
        return mealRepository.findAll()
                .stream()
                .filter(m -> m.getIngredientsList()
                        .stream()
                        .anyMatch(ingredients1 -> Arrays.stream(ingredients)
                                .toList()
                                .contains(ingredients1.getName())))
                .map(Meal::getName)
                //.map(Ingredients::getName)
                //.map(Ingredients::getMeal)
                .toList().toString();


    }

}
