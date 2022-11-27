package com.example.foodrecipeapp.exception;

public class NotFoundIngredientException extends Exception {
    public NotFoundIngredientException() {
        super("Not found ingredient");
    }
}
