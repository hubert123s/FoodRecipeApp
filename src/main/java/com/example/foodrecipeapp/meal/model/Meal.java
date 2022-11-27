package com.example.foodrecipeapp.meal.model;

import com.example.foodrecipeapp.ingredient.model.Ingredient;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @OneToMany(mappedBy = "meal", cascade = CascadeType.REMOVE)
    private List<Ingredient> ingredientList;
    @NotNull
    private Integer preperationTime;
    @NotBlank
    private String description;
    @Enumerated(EnumType.STRING)
    private TypeMeal typeMeal;

    public String emailFormat() {
        return "name=" + name + "\n" +
                "preperation time=" + preperationTime + "\n" +
                "description=" + description + "\n" +
                "type meal=" + typeMeal + "\n" +
                "ingredients :";
    }
}
