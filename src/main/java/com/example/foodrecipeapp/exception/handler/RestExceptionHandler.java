package com.example.foodrecipeapp.exception.handler;

import com.example.foodrecipeapp.exception.DuplicatedEmailException;
import com.example.foodrecipeapp.exception.DuplicatedMealException;
import com.example.foodrecipeapp.exception.NotFoundMealException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundMealException.class)
    ResponseEntity<String> handleNotFoundMealException(NotFoundMealException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(DuplicatedMealException.class)
    ResponseEntity<String> handleDuplicatedMealException(DuplicatedMealException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
    @ExceptionHandler(DuplicatedEmailException.class)
    ResponseEntity<String> handleDuplicatedEmailException(DuplicatedEmailException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

}
