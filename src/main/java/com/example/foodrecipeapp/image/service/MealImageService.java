package com.example.foodrecipeapp.image.service;

import com.example.foodrecipeapp.image.client.MealImageClient;
import com.example.foodrecipeapp.image.dto.MealImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MealImageService {
    private final MealImageClient mealImageClient;

    public String getImageURL(String name) {
        return getAllImageURL(name).get(0).getWebformatURL();
    }
    public List<MealImageDto> getAllImageURL(String name){
        return mealImageClient
                .getImages(name)
                .getHits()
                .stream()
                .sorted(Comparator.comparingLong(MealImageDto::getViews).thenComparing(MealImageDto::getViews))
                .toList();
    }
}
