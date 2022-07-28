package com.example.foodrecipeapp.Ingredients;


import com.example.foodrecipeapp.Meal.Meal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
public class Ingredients {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer amount;
    @ManyToOne(optional = false)
    @JsonIgnore
    private Meal meal;


}
