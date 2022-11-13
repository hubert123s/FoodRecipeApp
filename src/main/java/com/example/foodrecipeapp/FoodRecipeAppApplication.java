package com.example.foodrecipeapp;

import com.example.foodrecipeapp.Meal.model.Meal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class FoodRecipeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodRecipeAppApplication.class, args);

    }

}
