package com.example.foodrecipeapp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Wrong given meal id")
public class NotFoundMealException extends RuntimeException {
}
