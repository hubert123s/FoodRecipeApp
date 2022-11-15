package com.example.foodrecipeapp.exceptions.handler;

import com.example.foodrecipeapp.exceptions.DuplicatedEmailException;
import com.example.foodrecipeapp.exceptions.DuplicatedMealException;
import com.example.foodrecipeapp.exceptions.NotFoundMealException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundMealException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<String> handleNotFoundMealException(NotFoundMealException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(DuplicatedMealException.class)
    @ResponseStatus(BAD_REQUEST)
    ResponseEntity<String> handleDuplicatedMealException(DuplicatedMealException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
    @ExceptionHandler(DuplicatedEmailException.class)
    @ResponseStatus(BAD_REQUEST)
    ResponseEntity<String> handleDuplicatedEmailException(DuplicatedEmailException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

}
