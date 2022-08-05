package com.example.foodrecipeapp.Meal;

import com.example.foodrecipeapp.Ingredients.Ingredients;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank
    private String name;
    @OneToMany(mappedBy = "meal",cascade =CascadeType.REMOVE)
    private List<Ingredients> ingredientsList;
    @Size(min=2,max=90)
    private  Integer preperationTime;
    @NotBlank
    private String description;
    @Enumerated(EnumType.STRING)
    private TypeMeal typeMeal;
}
