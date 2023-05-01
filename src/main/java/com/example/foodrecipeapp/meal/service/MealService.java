package com.example.foodrecipeapp.meal.service;

import com.example.foodrecipeapp.exception.DuplicatedMealException;
import com.example.foodrecipeapp.exception.NotFoundMealException;
import com.example.foodrecipeapp.ingredient.dto.IngredientDto;
import com.example.foodrecipeapp.ingredient.mapper.IngredientDtoMapper;
import com.example.foodrecipeapp.meal.dto.MealDto;
import com.example.foodrecipeapp.meal.mapper.MealDtoMapper;
import com.example.foodrecipeapp.meal.model.Meal;
import com.example.foodrecipeapp.meal.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final IngredientDtoMapper ingredientDtoMapper;

    public MealDto getMealById(Long id) throws NotFoundMealException {
        return mealRepository.findById(id)
                .map(mealDtoMapper::toDto)
                .orElseThrow(NotFoundMealException::new);
    }

    public List<MealDto> getAllMeals(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Meal> pagedMeals = mealRepository.findAll(pageable);
        return pagedMeals.map(mealDtoMapper::toDto)
                .stream()
                .toList();
    }

    public List<IngredientDto> getIngredientsByMealId(Long id) throws NotFoundMealException {
        return mealRepository.findById(id)
                .orElseThrow(NotFoundMealException::new)
                .getIngredientList()
                .stream()
                .map(ingredientDtoMapper::map)
                .toList();
    }

    public MealDto saveMeal(MealDto mealDto) throws DuplicatedMealException {
        if (mealRepository.existsByName(mealDto.getName())) {
            throw new DuplicatedMealException(mealDto.getName());
        }
        Meal meal = mealDtoMapper.toEntity(mealDto);
        Meal savedMeal = mealRepository.save(meal);
        return mealDtoMapper.toDto(savedMeal);
    }

    public MealDto replaceMeal(Long mealId, MealDto mealDto) throws NotFoundMealException {
        if (!mealRepository.existsById(mealId)) {
            throw new NotFoundMealException();
        }
        mealDto.setId(mealId);
        Meal mealToUpdate = mealDtoMapper.toEntity(mealDto);
        Meal updateEntity = mealRepository.save(mealToUpdate);
        return mealDtoMapper.toDto(updateEntity);
    }

    public void deleteMeal(Long id) throws NotFoundMealException {
        if (!mealRepository.existsById(id)) {
            throw new NotFoundMealException();
        }
        mealRepository.deleteById(id);
    }

    public MealDto findByName(String name) throws NotFoundMealException {
        return mealRepository.findByName(name).map(mealDtoMapper::toDto)
                .orElseThrow(NotFoundMealException::new);
    }

    public List<MealDto> findWithOutFewIngredients(String... ingredients) {
        return mealRepository.findByIngredientsNotIn(Arrays.asList(ingredients))
                .stream()
                .map(mealDtoMapper::toDto)
                .toList();
    }
}
