package com.example.foodrecipeapp.Newsletter.Subscriber.model;

import com.example.foodrecipeapp.Newsletter.TypeNewsletter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscriber")
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    private TypeNewsletter typeNewsletter=TypeNewsletter.daily;

}
