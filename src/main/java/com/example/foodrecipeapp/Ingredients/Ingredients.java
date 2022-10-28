package com.example.foodrecipeapp.Ingredients;


import com.example.foodrecipeapp.Meal.Meal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Ingredients {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Size(min =1,max=1000)
    private Integer amount;
    @ManyToOne(optional = false)
    @JsonIgnore
    private Meal meal;


    public String emailFormat() {
        return " name=" + name + '\'' +
                "amount=" + amount;
    }
}
