package com.example.foodrecipeapp.Ingredients;

import com.example.foodrecipeapp.Meal.Meal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    MockMvc mockMvc;
    @Test
    void shouldGetIngredientsById() throws Exception {
        //given
        Ingredients chleb = new Ingredients();
        chleb.setId(1L);
        chleb.setName("chleb");
        chleb.setAmount(2);
        chleb.setMeal(new Meal());
        ingredientsRepository.save(chleb);
        //when
        mockMvc.perform(get("/ingredients/"+chleb.getId()))
                .andDo(print())
                .andExpect(status().is(200));
        //then
        assertThat(chleb).isNotNull();
        assertThat(chleb.getName()).isEqualTo("chleb");


    }
}