package com.example.foodrecipeapp.Newsletter;

import com.example.foodrecipeapp.Meal.model.Meal;
import com.example.foodrecipeapp.Meal.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
@RequiredArgsConstructor
@Component
public class ContentGenerator {
    private final MealRepository mealRepository;

    public byte[] ebookGenerator()
    {
        List<String> allNameMeals = mealRepository.findAllNameandSortByStatus();
        List<String> text= mealRepository.sortByStatus().stream().map(Meal::emailFormat).toList();
        return (createContent(allNameMeals)+createContent(text)).getBytes();
    }
    public String createContent(List<String> content)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(String string :content)
        {
            stringBuilder.append(string);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
