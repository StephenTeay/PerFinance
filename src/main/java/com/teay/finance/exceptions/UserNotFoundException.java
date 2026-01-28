package com.teay.finance.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;


public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){}

    public UserNotFoundException(String message){
        super(message);
    }
}