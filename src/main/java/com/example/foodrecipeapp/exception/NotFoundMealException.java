package com.example.foodrecipeapp.exception;

public class NotFoundMealException extends Exception {
    public NotFoundMealException() {
        super("Not found meal");
    }
}
