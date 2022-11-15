package com.example.foodrecipeapp.image.service;

import com.example.foodrecipeapp.image.MealImageClient;
import org.springframework.stereotype.Service;


@Service
public class MealImageService {

    private final MealImageClient mealImageClient;

    public MealImageService(MealImageClient mealImageClient) {
        this.mealImageClient = mealImageClient;
    }


    public String getImageURL(String name) {
        return mealImageClient.getImages(name).getHits().get(0).getWebformatURL();
    }
}
