package com.example.foodrecipeapp.ingredient.model;


import com.example.foodrecipeapp.meal.model.Meal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NonNull
    private Integer amount;
    //@ManyToOne(optional = false)
    @ManyToOne(optional = false)
    @JsonIgnore
    private Meal meal;


    public String emailFormat() {
        return " name=" + name + "amount=" + amount + "\n";
    }
}
