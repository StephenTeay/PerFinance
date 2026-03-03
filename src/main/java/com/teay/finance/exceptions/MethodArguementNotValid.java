package com.teay.finance.exceptions;

public class MethodArguementNotValid extends RuntimeException {
    public MethodArguementNotValid(String message) {
      message = "Invalid Input";
      //super(message);
    }
}
