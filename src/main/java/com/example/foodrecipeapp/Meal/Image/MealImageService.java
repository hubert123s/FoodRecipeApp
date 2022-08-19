package com.example.foodrecipeapp.Meal.Image;

import org.springframework.stereotype.Service;

@Service

public class MealImageService {

    private final MealImageClient mealImageClient;

    public MealImageService(MealImageClient mealImageClient) {
        this.mealImageClient = mealImageClient;
    }


    public String getImageURL(String name)
    {
       return mealImageClient.getImages(name).getHits().get(0).getWebformatURL();
    }
}
