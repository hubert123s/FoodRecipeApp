package com.example.foodrecipeapp.ingredient;

import com.example.foodrecipeapp.ingredient.dto.IngredientDto;
import com.example.foodrecipeapp.ingredient.model.Ingredient;
import com.example.foodrecipeapp.ingredient.repository.IngredientRepository;
import com.example.foodrecipeapp.ingredient.service.IngredientService;
import com.example.foodrecipeapp.meal.model.Meal;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class IngredientControllerTest {

    @Autowired
    IngredientService ingredientService;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldGetIngredientsById() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("ingredients");
        ingredient.setAmount(2);
        ingredient.setMeal(new Meal());
        ingredientRepository.save(ingredient);
        MvcResult mvcResult = mockMvc.perform(get("/ingredient/" + ingredient.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        Ingredient responseIngredient = objectMapper.readValue(contentAsString, Ingredient.class);
        Assertions.assertEquals(ingredient.getName(), responseIngredient.getName());
        Assertions.assertEquals(ingredient.getAmount(), responseIngredient.getAmount());


    }

    @Test
    void shouldPostIngredientsById() throws Exception {
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(1L);
        ingredientDto.setName("ingredientsDto");
        ingredientDto.setAmount(2);
        ingredientDto.setMealId(1L);

        MvcResult mvcResult = mockMvc.perform(post("/ingredient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(ingredientDto))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        IngredientDto responseIngredients = objectMapper.readValue(contentAsString, IngredientDto.class);
        Assertions.assertEquals(ingredientDto.getName(), responseIngredients.getName());
        Assertions.assertEquals(ingredientDto.getAmount(), responseIngredients.getAmount());

    }

    @Test
    void shouldDeleteIngredientsById() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("ingredients");
        ingredient.setAmount(2);
        mockMvc.perform(delete("/ingredient/" + ingredient.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.name").doesNotExist())
                .andExpect(jsonPath("$.amount").doesNotExist());
    }
}