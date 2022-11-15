package com.example.foodrecipeapp.exceptions;

public class DuplicatedMealException extends Exception {
    public DuplicatedMealException(String name) {
        super("Meal %s in use".formatted(name));
    }
}
