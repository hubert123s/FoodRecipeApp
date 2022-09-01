package com.example.foodrecipeapp.Meal.Image;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Service;

import java.util.List;

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
