package com.example.foodrecipeapp.image;

import com.example.foodrecipeapp.image.dto.ApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class MealImageClient {
    @Qualifier("restTemplateConfiguration")
    private final RestTemplate restTemplate;
    @Value("${apiKey}")
    private String API_KEY ;
    private static final String IMAGE_URL = "https://pixabay.com/api/";
    public ApiDto getImages(String name) {
        return restTemplate.getForObject(IMAGE_URL + "?key={API_KEY}&q={name}", ApiDto.class, API_KEY, name);
    }
}
