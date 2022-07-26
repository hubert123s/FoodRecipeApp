package com.example.foodrecipeapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsDto implements Serializable {
    private  Long id;
    private  String name;
    private  Integer amount;
}
