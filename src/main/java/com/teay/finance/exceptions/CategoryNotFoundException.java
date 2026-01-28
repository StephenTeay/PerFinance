package com.teay.finance.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(){}

    public CategoryNotFoundException(String message){
        super(message);
    }
}