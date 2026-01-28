package com.teay.finance.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(){}

    public TransactionNotFoundException(String message){
        super(message);
    }
}