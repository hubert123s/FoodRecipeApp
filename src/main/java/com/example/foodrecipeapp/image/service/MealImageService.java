package com.example.foodrecipeapp.image.service;

import com.example.foodrecipeapp.image.MealImageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MealImageService {
    private final MealImageClient mealImageClient;

    public String getImageURL(String name) {
        return mealImageClient.getImages(name).getHits().get(0).getWebformatURL();
    }
}
