package com.teay.finance.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransactionException extends RuntimeException{
    public TransactionException(){}

    public TransactionException(String message){
        super(message);
    }
}