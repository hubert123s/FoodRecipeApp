package com.example.foodrecipeapp;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "meal")
    private List<Ingredients> ingredientsList;
    private  Integer preperationTime;
    private String description;
    @Enumerated(EnumType.STRING)
    private  TypeMeal typeMeal;
}
