package com.example.foodrecipeapp.newsletter.controller;

import com.example.foodrecipeapp.exception.DuplicatedEmailException;
import com.example.foodrecipeapp.newsletter.model.Subscriber;
import com.example.foodrecipeapp.newsletter.model.TypeNewsletter;
import com.example.foodrecipeapp.newsletter.service.SubscriberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SubscriberControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SubscriberService subscriberService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldAddSubscriber() throws Exception {

        Subscriber subscriber = Subscriber.builder()
                .name("janek")
                .email("12345@gmail.com")
                .typeNewsletter(TypeNewsletter.DAILY)
                .createdDate(LocalDateTime.now())
                .build();

        MvcResult mvcResult = mockMvc.perform(post("/subscriber/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(subscriber))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is(200)).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        Subscriber responseSubscriber = objectMapper.readValue(contentAsString, Subscriber.class);
        assertEquals(subscriber.getName(), responseSubscriber.getName());
        assertEquals(subscriber.getEmail(), responseSubscriber.getEmail());
        assertEquals(subscriber.getTypeNewsletter(), responseSubscriber.getTypeNewsletter());
        assertThrows(DuplicatedEmailException.class, () -> subscriberService.addSubscriber(subscriber));
    }

    @Test
    void shouldSaveYourDishes() throws Exception {
        MockMultipartFile recipe = new MockMultipartFile("file", "recipe.txt", MediaType.APPLICATION_OCTET_STREAM_VALUE, new byte[0]);

        mockMvc.perform(multipart("/subscriber/your-dishes")
                        .file(recipe)
                        .param("author", "123@gmail.com")
                        .with(processor -> {
                            processor.setMethod("POST");
                            return processor;
                        }))
                .andExpect(status().is(200));
    }
}