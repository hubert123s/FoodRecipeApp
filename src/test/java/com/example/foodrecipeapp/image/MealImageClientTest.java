package com.example.foodrecipeapp.image;

import com.example.foodrecipeapp.image.dto.ApiDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class MealImageClientTest {

    @Mock
    private  RestTemplate restTemplate;
    @InjectMocks
    private MealImageClient mealImageClient;
    @Value("${apiKey}")
    private String API_KEY ;
    private static final String IMAGE_URL = "https://pixabay.com/api/";

    @Test
    void getImagesWithCorrectUrl() {
        String name = "pizza";
        mealImageClient.getImages(name);
        verify(restTemplate).getForObject(IMAGE_URL + "?key={API_KEY}&q={name}", ApiDto.class,API_KEY,name);
    }
}
