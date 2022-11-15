package com.example.foodrecipeapp.ingredients.model;


import com.example.foodrecipeapp.meal.model.Meal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Size(min = 1, max = 1000)
    private Integer amount;
    @ManyToOne(optional = false)
    @JsonIgnore
    private Meal meal;


    public String emailFormat() {
        return " name=" + name + "amount=" + amount + "\n";
    }
}
