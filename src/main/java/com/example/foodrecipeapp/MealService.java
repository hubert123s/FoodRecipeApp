package com.example.foodrecipeapp;

import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MealService {
    private  final MealRepository mealRepository;
    private  final  MealDtoMapper mealDtoMapper;

    public MealService(MealRepository mealRepository, MealDtoMapper mealDtoMapper) {
        this.mealRepository = mealRepository;
        this.mealDtoMapper = mealDtoMapper;
    }

    Optional<MealDto> getMealById(Long id)
    {
        return mealRepository.findById(id)
                .map(mealDtoMapper::map);
    }
}
