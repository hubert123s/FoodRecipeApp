package com.example.foodrecipeapp.exception;

public class DuplicatedEmailException extends Exception{
    public DuplicatedEmailException(String name) {
        super("Email %s in use".formatted(name));
    }
}
