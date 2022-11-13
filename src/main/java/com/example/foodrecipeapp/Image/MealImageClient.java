package com.example.foodrecipeapp.Image;

import com.example.foodrecipeapp.Image.dto.ApiDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MealImageClient {
    private RestTemplate restTemplate = new RestTemplate();
    private static final String API_KEY="29294717-ad04b5f26155f338cc1b5c55d";
    private static final String IMAGE_URL ="https://pixabay.com/api/";

    public ApiDto getImages(String name)
    {
        return  restTemplate.getForObject(IMAGE_URL+"?key={API_KEY}&q={name}",ApiDto.class,API_KEY,name);
    }

}
