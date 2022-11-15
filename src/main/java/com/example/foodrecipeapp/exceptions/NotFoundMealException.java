package com.example.foodrecipeapp.exceptions;

public class NotFoundMealException extends Exception {
    public NotFoundMealException() {
        super("Not found meal");
    }
}
