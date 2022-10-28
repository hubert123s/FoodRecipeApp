package com.example.foodrecipeapp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Meal taken")
public class DuplicatedMealException extends RuntimeException {
    public DuplicatedMealException(){
        super("Meal taken");
    }
}
