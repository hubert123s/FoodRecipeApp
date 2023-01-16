package com.example.foodrecipeapp.meal.controller;

import com.example.foodrecipeapp.exception.DuplicatedMealException;
import com.example.foodrecipeapp.ingredient.dto.IngredientDto;
import com.example.foodrecipeapp.ingredient.mapper.IngredientDtoMapper;
import com.example.foodrecipeapp.ingredient.model.Ingredient;
import com.example.foodrecipeapp.ingredient.repository.IngredientRepository;
import com.example.foodrecipeapp.meal.dto.MealDto;
import com.example.foodrecipeapp.meal.mapper.MealDtoMapper;
import com.example.foodrecipeapp.meal.model.Meal;
import com.example.foodrecipeapp.meal.model.TypeMeal;
import com.example.foodrecipeapp.meal.repository.MealRepository;
import com.example.foodrecipeapp.meal.service.MealService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MealControllerTest {
    @Autowired
    MealRepository mealRepository;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    MealService mealService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    MealDtoMapper mealDtoMapper;
    @Autowired
    IngredientDtoMapper ingredientDtoMapper;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnMealById() throws Exception {


        Meal meal = mealRepository.save(Meal.builder()
                .name("pizza")
                .preparationTime(10)
                .description("coming soon")
                .typeMeal(TypeMeal.DINNER)
                .build());
        MvcResult mvcResult = mockMvc.perform(get("/meal/" + meal.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        MealDto responseMeal = objectMapper.readValue(contentAsString, MealDto.class);
        Assertions.assertEquals(meal.getName(), responseMeal.getName());
        Assertions.assertEquals(meal.getDescription(), responseMeal.getDescription());
        Assertions.assertEquals(meal.getTypeMeal(), responseMeal.getTypeMeal());
        Assertions.assertEquals(meal.getPreparationTime(), responseMeal.getPreparationTime());
    }

    @Test
    void shouldPostMeal() throws Exception {

        MealDto mealDto = MealDto.builder()
                .name("pizza")
                .preparationTime(10)
                .description("coming soon")
                .typeMeal(TypeMeal.DINNER)
                .build();

        MvcResult mvcResult = mockMvc.perform(post("/meal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(mealDto))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is(200)).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        MealDto responseMealDto = objectMapper.readValue(contentAsString, MealDto.class);
        Assertions.assertEquals(mealDto.getName(), responseMealDto.getName());
        Assertions.assertEquals(mealDto.getDescription(), responseMealDto.getDescription());
        Assertions.assertEquals(mealDto.getTypeMeal(), responseMealDto.getTypeMeal());
        Assertions.assertEquals(mealDto.getPreparationTime(), responseMealDto.getPreparationTime());
        Assertions.assertThrows(DuplicatedMealException.class, () -> mealService.saveMeal(mealDto));


    }

    @Test
    void shouldDeleteMeal() throws Exception {

        Meal meal = mealRepository.save(Meal.builder()
                .name("pizza")
                .preparationTime(10)
                .description("coming soon")
                .typeMeal(TypeMeal.DINNER)
                .build());

        mockMvc.perform(delete("/meal/" + meal.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.name").doesNotExist())
                .andExpect(jsonPath("$.preperationTime").doesNotExist())
                .andExpect(jsonPath("$.description").doesNotExist())
                .andExpect(jsonPath("$.typeMeal").doesNotExist());

    }

    @Test
    void shouldReplaceMeal() throws Exception {
        Meal meal = mealRepository.save(Meal.builder()
                .name("pizza")
                .preparationTime(10)
                .description("coming soon")
                .typeMeal(TypeMeal.DINNER)
                .build());
        MealDto mealDto = MealDto.builder()
                .name("pizza")
                .preparationTime(10)
                .description("coming soon")
                .typeMeal(TypeMeal.DINNER)
                .build();

        MvcResult mvcResult = mockMvc.perform(put("/meal/" + meal.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(mealDto))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        MealDto responseMeal = objectMapper.readValue(contentAsString, MealDto.class);
        Assertions.assertEquals(meal.getName(), responseMeal.getName());
        Assertions.assertEquals(meal.getDescription(), responseMeal.getDescription());
        Assertions.assertEquals(meal.getTypeMeal(), responseMeal.getTypeMeal());
        Assertions.assertEquals(meal.getPreparationTime(), responseMeal.getPreparationTime());

    }

    @Test
    void shouldFindByName() throws Exception {
        Meal meal = mealRepository.save(Meal.builder()
                .name("pizza")
                .preparationTime(10)
                .description("coming soon")
                .typeMeal(TypeMeal.DINNER)
                .build());
        mockMvc.perform(get("/meal/name")
                        .queryParam("name", meal.getName()))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.description").value(meal.getDescription()));

    }

    @Test
    void shouldNotFindByName() throws Exception {
        mockMvc.perform(get("/meal/name")
                        .queryParam("name", "dfdvs"))
                .andDo(print())
                .andExpect(status().is(404));
    }


}
