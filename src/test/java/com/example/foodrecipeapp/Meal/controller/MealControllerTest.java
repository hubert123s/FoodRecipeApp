package com.example.foodrecipeapp.Meal.controller;

import com.example.foodrecipeapp.Meal.dto.MealDto;
import com.example.foodrecipeapp.Meal.mapper.MealDtoMapper;
import com.example.foodrecipeapp.Meal.model.Meal;
import com.example.foodrecipeapp.Meal.model.TypeMeal;
import com.example.foodrecipeapp.Meal.repository.MealRepository;
import com.example.foodrecipeapp.Meal.service.MealService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

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
    MealService mealService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    MealDtoMapper mealDtoMapper;
    @Autowired
    ObjectMapper objectMapper;
    @Test
    void shouldReturnMealById() throws Exception {
        Meal meal = new Meal();
        meal.setId(1L);
        meal.setName("pizza");
        meal.setPreperationTime(Integer.valueOf(10));
        meal.setDescription("coming soon");
        meal.setTypeMeal(TypeMeal.dinner);

        mealRepository.save(meal);
        mockMvc.perform(get("/meal/"+meal.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(meal.getId()))
                .andExpect(jsonPath("$.name").value(meal.getName()))
                .andExpect(jsonPath("$.preperationTime").value(meal.getPreperationTime()))
                .andExpect(jsonPath("$.description").value(meal.getDescription()))
                .andExpect(jsonPath("$.typeMeal").value(meal.getTypeMeal().name()));
    }
    @Test
    void shouldPostMeals() throws Exception {
        MealDto meal = new MealDto();
        meal.setId(1L);
        meal.setName("pizza");
        meal.setPreperationTime(Integer.valueOf(10));
        meal.setDescription("coming soon");
        meal.setTypeMeal(TypeMeal.dinner);

        mockMvc.perform(post("/meal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(meal))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.id").value(meal.getId()))
                .andExpect(jsonPath("$.name").value(meal.getName()))
                .andExpect(jsonPath("$.preperationTime").value(meal.getPreperationTime()))
                .andExpect(jsonPath("$.description").value(meal.getDescription()))
                .andExpect(jsonPath("$.typeMeal").value(meal.getTypeMeal().name()));
    }
    @Test
    void shouldDeleteMeal() throws Exception {
        Meal meal = new Meal();
        meal.setId(1L);
        meal.setName("pizza");
        meal.setPreperationTime(Integer.valueOf(10));
        meal.setDescription("coming soon");
        meal.setTypeMeal(TypeMeal.dinner);

        mealRepository.save(meal);
        mockMvc.perform(delete("/meal/"+meal.getId()))
                .andDo(print())
                .andExpect(status().is(204))
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.name").doesNotExist())
                .andExpect(jsonPath("$.preperationTime").doesNotExist())
                .andExpect(jsonPath("$.description").doesNotExist())
                .andExpect(jsonPath("$.typeMeal").doesNotExist());
    }
    @Test
    void shouldReplaceMeal() throws Exception {
        Meal meal = new Meal();
        meal.setId(1L);
        meal.setName("pizza");
        meal.setPreperationTime(Integer.valueOf(10));
        meal.setDescription("coming soon");
        meal.setTypeMeal(TypeMeal.dinner);

        mealRepository.save(meal);

        MealDto meal2 = new MealDto();
        meal2.setId(1L);
        meal2.setName("pizza");
        meal2.setPreperationTime(Integer.valueOf(10));
        meal2.setDescription("");
        meal2.setTypeMeal(TypeMeal.dinner);
        mockMvc.perform(put("/meal/"+meal.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(meal2))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(meal2.getId()))
                .andExpect(jsonPath("$.name").value(meal2.getName()))
                .andExpect(jsonPath("$.preperationTime").value(meal2.getPreperationTime()))
                .andExpect(jsonPath("$.description").value(meal2.getDescription()))
                .andExpect(jsonPath("$.typeMeal").value(meal2.getTypeMeal().name()));

    }
    @Test
    void shouldFindByName() throws Exception {
        mockMvc.perform(get("/meal/name?name=toast"))
                .andDo(print())
                .andExpect(status().is(200));
        mockMvc.perform(get("/meal/name?name=skfcns"))
                .andDo(print())
                .andExpect(status().is(404));



    }
}