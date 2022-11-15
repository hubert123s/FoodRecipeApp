package com.example.foodrecipeapp.ingredients;

import com.example.foodrecipeapp.ingredients.dto.IngredientsDto;
import com.example.foodrecipeapp.ingredients.model.Ingredients;
import com.example.foodrecipeapp.ingredients.repository.IngredientsRepository;
import com.example.foodrecipeapp.ingredients.service.IngredientsService;
import com.example.foodrecipeapp.meal.dto.MealDto;
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
class IngredientsControllerTest {

    @Autowired
    IngredientsService ingredientsService;
    @Autowired
    IngredientsRepository ingredientsRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldGetIngredientsById() throws Exception {
        Ingredients ingredients = new Ingredients();
        ingredients.setId(1L);
        ingredients.setName("ingredients");
        ingredients.setAmount(2);
        ingredients.setMeal(new Meal());
        ingredientsRepository.save(ingredients);
        MvcResult mvcResult = mockMvc.perform(get("/ingredients/" + ingredients.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        Ingredients responseIngredients = objectMapper.readValue(contentAsString, Ingredients.class);
        Assertions.assertEquals(ingredients.getName(), responseIngredients.getName());
        Assertions.assertEquals(ingredients.getAmount(), responseIngredients.getAmount());


    }

    @Test
    void shouldPostIngredientsById() throws Exception {
        IngredientsDto ingredientsDto = new IngredientsDto();
        ingredientsDto.setId(1L);
        ingredientsDto.setName("ingredientsDto");
        ingredientsDto.setAmount(2);
        ingredientsDto.setMealId(1L);

        MvcResult mvcResult = mockMvc.perform(post("/ingredients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(ingredientsDto))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is(201))
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        IngredientsDto responseIngredients = objectMapper.readValue(contentAsString, IngredientsDto.class);
        Assertions.assertEquals(ingredientsDto.getName(), responseIngredients.getName());
        Assertions.assertEquals(ingredientsDto.getAmount(), responseIngredients.getAmount());

    }

    @Test
    void shouldDeleteIngredientsById() throws Exception {
        Ingredients ingredients = new Ingredients();
        ingredients.setId(1L);
        ingredients.setName("ingredients");
        ingredients.setAmount(2);
        mockMvc.perform(delete("/ingredients/" + ingredients.getId()))
                .andDo(print())
                .andExpect(status().is(204))
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.name").doesNotExist())
                .andExpect(jsonPath("$.amount").doesNotExist());
    }
}