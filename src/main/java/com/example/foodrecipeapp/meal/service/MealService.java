package com.example.foodrecipeapp.meal.service;

import com.example.foodrecipeapp.exception.DuplicatedMealException;
import com.example.foodrecipeapp.exception.NotFoundMealException;
import com.example.foodrecipeapp.ingredients.dto.IngredientsDto;
import com.example.foodrecipeapp.ingredients.mapper.IngredientsDtoMapper;
import com.example.foodrecipeapp.meal.dto.MealDto;
import com.example.foodrecipeapp.meal.mapper.MealDtoMapper;
import com.example.foodrecipeapp.meal.model.Meal;
import com.example.foodrecipeapp.meal.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;
    private final MealDtoMapper mealDtoMapper;
    private final IngredientsDtoMapper ingredientsDtoMapper;

    public MealDto getMealById(Long id) throws NotFoundMealException {
        return mealRepository.findById(id)
                .map(MealDtoMapper::toDto)
                .orElseThrow(() -> new NotFoundMealException());
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

    public List<IngredientsDto> getIngredientsByMealId(Long id) throws NotFoundMealException {
        return mealRepository.findById(id)
                .orElseThrow(() -> new NotFoundMealException())
                .getIngredientsList()
                .stream()
                .map(ingredientsDtoMapper::map)
                .toList();
    }

    public MealDto saveMeal(MealDto mealDto) throws DuplicatedMealException {
        if (mealRepository.existsByName(mealDto.getName())) {
            throw new DuplicatedMealException(mealDto.getName());
        }
        Meal meal = mealDtoMapper.toEntity(mealDto);
        Meal savedMeal = mealRepository.save(meal);
        return MealDtoMapper.toDto(savedMeal);
    }

    public MealDto replaceMeal(Long mealId, MealDto mealDto) throws NotFoundMealException {
        if (!mealRepository.existsById(mealId)) {
            throw new NotFoundMealException();
        }
        mealDto.setId(mealId);
        Meal mealToUpdate = mealDtoMapper.toEntity(mealDto);
        Meal updateEntity = mealRepository.save(mealToUpdate);
        return MealDtoMapper.toDto(updateEntity);
    }

    public void deleteMeal(Long id) throws NotFoundMealException {
        if (!mealRepository.existsById(id)) {
            throw new NotFoundMealException();
        }
        mealRepository.deleteById(id);
    }

    public MealDto findByName(String name) throws NotFoundMealException {
        return mealRepository.findByName(name).map(MealDtoMapper::toDto)
                .orElseThrow(() -> new NotFoundMealException());
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

    public List<MealDto> findWithOutFewIngredients(String... ingredients) {
        return mealRepository.findAll()
                .stream()
                .filter(m -> m.getIngredientsList()
                        .stream()
                        .noneMatch(ingredients1 -> Arrays.stream(ingredients)
                                .toList()
                                .contains(ingredients1.getName())))
                .map(MealDtoMapper::toDto)
                .toList();


    }

}
