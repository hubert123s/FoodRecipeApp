package com.example.foodrecipeapp.meal.controller;

import com.example.foodrecipeapp.exception.DuplicatedMealException;
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

    @Test
    void shouldFindWithoutFewIngredients() throws Exception {
        Ingredient ingredient = Ingredient.builder()
                .name("egg")
                .amount(2)
                .build();
        Meal meal = Meal.builder()
                .name("Scrambled eggs")
                .preparationTime(10)
                .description("coming soon")
                .typeMeal(TypeMeal.BREAKFAST)
                .ingredientList(List.of(ingredient))
                .build();

        mealRepository.save(meal);

        mockMvc.perform(get("/meal/ingredients")
                        .queryParam("without", "salt,cheese"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.[0].name").value(meal.getName()));

    }

    @Test
    void shouldGetIngredientsByMealId() throws Exception {
        Ingredient ingredient = Ingredient.builder()
                .name("egg")
                .amount(2)
                .meal(new Meal())
                .build();
        Meal mealDto = Meal.builder()
                .name("Scrambled eggs")
                .preparationTime(10)
                .description("coming soon")
                .typeMeal(TypeMeal.BREAKFAST)
                .ingredientList(List.of(ingredient))
                .build();
        Meal meal = mealRepository.save(mealDto);

        mockMvc.perform(get("/meal/" + meal.getId() + "/ingredients"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.[0].name").value(ingredient.getName()));
    }
//    @Test
//    void shouldGetIngredientsById() throws Exception {
////        Ingredient ingredient = ingredientRepository.save(Ingredient.builder()
////                .name("egg")
////                .amount(Integer.valueOf(2))
////                .build());
//
//
//        Meal meal = mealRepository.save(Meal.builder()
//                .name("Scrambled eggs")
//                .preparationTime(10)
//                .description("coming soon")
//                .typeMeal(TypeMeal.BREAKFAST)
//                //.ingredientList(List.of(ingredient))
//                .build());
//        System.out.println("to jest meal.getid(" + meal.getId());
//        Ingredient ingredient = ingredientRepository.save(ingredientDtoMapper.toEntity(IngredientDto.builder()
//                .name("egg")
//                .amount(Integer.valueOf(2))
//                .mealId(meal.getId())
//                .build()));
//        System.out.println(mealRepository.findAll());
//        List<Meal> listameali= mealRepository.findAll();
//        Meal meal2 = mealRepository.findById(meal.getId()).get();
//        mockMvc.perform(get("/meal/" + meal.getId() + "/ingredients"))
//                .andDo(print())
//                .andExpect(status().is(200))
//                .andExpect(jsonPath("$.[0].name").value(ingredient.getName()));
//    }
//
//    //NOOOOOOOOOOOOOOOOOOOOOWE
//    @Test
//    void shouldGetIngredientsByMealId() throws Exception {
////        Ingredient ingredient = ingredientRepository.save(Ingredient.builder()
////                .name("egg")
////                .amount(Integer.valueOf(2))
////                .build());
//
//
//        MealDto mealDto = MealDto.builder()
//                .name("Scrambled eggs")
//                .preparationTime(10)
//                .description("coming soon")
//                .typeMeal(TypeMeal.BREAKFAST)
//                //.ingredientList(List.of(ingredient))
//                .build();
//        MvcResult mvcResult = mockMvc.perform(post("/meal")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(mealDto))
//                        .accept(MediaType.APPLICATION_JSON)
//                        .characterEncoding("utf-8"))
//                .andDo(print())
//                .andExpect(status().is(200)).andReturn();
//
//
//        Long mealId=4L;
//        System.out.println("to jest meal.getid(" + mealDto.getId());
//        Ingredient ingredientDto = ingredientRepository.save(ingredientDtoMapper.toEntity(IngredientDto.builder()
//                .name("egg")
//                .amount(Integer.valueOf(2))
//                .mealId(mealId)
//                .build()));
//         mockMvc.perform(post("/ingredient")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(ingredientDto))
//                        .accept(MediaType.APPLICATION_JSON)
//                        .characterEncoding("utf-8"))
//                .andDo(print())
//                .andExpect(status().is(200))
//                .andReturn();
//        System.out.println(mealRepository.findAll());
//        List<Meal> listameali= mealRepository.findAll();
//        Meal meal2 = mealRepository.findById(mealId).get();
//        mockMvc.perform(get("/meal/" + mealDto.getId() + "/ingredients"))
//                .andDo(print())
//                .andExpect(status().is(200))
//                .andExpect(jsonPath("$.[0].name").value(ingredientDto.getName()));
//    }

}
