package com.example.foodrecipeapp.ingredient.service;

import com.example.foodrecipeapp.exception.NotFoundIngredientException;
import com.example.foodrecipeapp.ingredient.dto.IngredientDto;
import com.example.foodrecipeapp.ingredient.mapper.IngredientDtoMapper;
import com.example.foodrecipeapp.ingredient.model.Ingredient;
import com.example.foodrecipeapp.ingredient.repository.IngredientRepository;
import com.example.foodrecipeapp.meal.model.Meal;
import com.example.foodrecipeapp.meal.model.TypeMeal;
import com.example.foodrecipeapp.meal.repository.MealRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;
    @Mock
    private MealRepository mealRepository;

    @Test
    void shouldSaveIngredient() {
        IngredientDtoMapper ingredientDtoMapper = new IngredientDtoMapper(mealRepository);
        IngredientService ingredientService = new IngredientService(ingredientRepository, ingredientDtoMapper);
        Ingredient ingredient = getIngredient();

        Mockito.when(ingredientRepository.save(any(Ingredient.class))).thenReturn(ingredient);
        Mockito.when(mealRepository.findById(anyLong())).thenReturn(Optional.of(new Meal()));
        IngredientDto retrieveIngredient = ingredientService.saveIngredients(ingredientDtoMapper.map(ingredient));
        assertThat(retrieveIngredient.getName()).isEqualTo(retrieveIngredient.getName());
        assertThat(retrieveIngredient.getAmount()).isEqualTo(retrieveIngredient.getAmount());


    }

    @Test
    void shouldDeleteIngredient() {
        IngredientDtoMapper ingredientDtoMapper = new IngredientDtoMapper(mealRepository);
        IngredientService ingredientService = new IngredientService(ingredientRepository, ingredientDtoMapper);
        Mockito.doNothing().when(ingredientRepository).deleteById(anyLong());

        ingredientService.deleteIngredients(anyLong());

        verify(ingredientRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(ingredientRepository);

    }

    @Test
    void shouldGetIngredientById() throws NotFoundIngredientException {
        IngredientDtoMapper ingredientDtoMapper = new IngredientDtoMapper(mealRepository);
        IngredientService ingredientService = new IngredientService(ingredientRepository, ingredientDtoMapper);
        Ingredient ingredient = getIngredient();
        Mockito.when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredient));
        IngredientDto retrieveIngredientDto = ingredientService.getIngredientsById(anyLong());
        assertThat(retrieveIngredientDto.getName()).isEqualTo(retrieveIngredientDto.getName());
        assertThat(retrieveIngredientDto.getAmount()).isEqualTo(retrieveIngredientDto.getAmount());


    }

    private Ingredient getIngredient() {
        Meal meal = Meal.builder()
                .id(1L)
                .name("kebab")
                .preparationTime(10)
                .description("coming soon")
                .typeMeal(TypeMeal.DINNER)
                .build();
        return new Ingredient(
                1L,
                "roll",
                10,
                meal);
    }
}
