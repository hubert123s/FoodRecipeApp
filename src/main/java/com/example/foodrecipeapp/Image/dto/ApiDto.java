package com.example.foodrecipeapp.Image.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiDto {
    private Double total;
    private Double totalHits;
    private List<MealImageDto> hits;
}
