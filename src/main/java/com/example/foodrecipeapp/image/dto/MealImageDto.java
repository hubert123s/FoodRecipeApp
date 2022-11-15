package com.example.foodrecipeapp.image.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealImageDto {
    private Long id;
    private String pageURL;
    private String type;
    private String tags;
    private String previewURL;
    private Double previewWidth;
    private String webformatURL;
}
