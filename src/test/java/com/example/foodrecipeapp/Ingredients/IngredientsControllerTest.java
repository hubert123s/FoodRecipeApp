package com.example.foodrecipeapp.Ingredients;

import com.example.foodrecipeapp.Ingredients.dto.IngredientsDto;
import com.example.foodrecipeapp.Ingredients.model.Ingredients;
import com.example.foodrecipeapp.Ingredients.repository.IngredientsRepository;
import com.example.foodrecipeapp.Ingredients.service.IngredientsService;
import com.example.foodrecipeapp.Meal.model.Meal;
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
        Ingredients chleb = new Ingredients();
        chleb.setId(1L);
        chleb.setName("chleb");
        chleb.setAmount(2);
        chleb.setMeal(new Meal());
        ingredientsRepository.save(chleb);
        mockMvc.perform(get("/ingredients/"+chleb.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(chleb.getId()))
                .andExpect(jsonPath("$.name").value("chleb"))
                .andExpect(jsonPath("$.amount").value(chleb.getAmount()));



    }
    @Test
    void shouldPostIngredientsById() throws Exception {
        IngredientsDto chleb = new IngredientsDto();
        chleb.setId(1L);
        chleb.setName("chleb");
        chleb.setAmount(2);
        chleb.setMealId(1L);

        mockMvc.perform(post("/ingredients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(chleb))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.id").value(chleb.getId()))
                .andExpect(jsonPath("$.name").value("chleb"))
                .andExpect(jsonPath("$.amount").value(chleb.getAmount()));
    }
    @Test
    void shouldDeleteIngredientsById() throws Exception {
        Ingredients chleb = new Ingredients();
        chleb.setId(1L);
        chleb.setName("chleb");
        chleb.setAmount(2);
       // ingredientsRepository.save(chleb);
        mockMvc.perform(delete("/ingredients/"+chleb.getId()))
                .andDo(print())
                .andExpect(status().is(204))
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.name").doesNotExist())
                .andExpect(jsonPath("$.amount").doesNotExist());
    }
}