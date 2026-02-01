package com.pragma.challenge.msvc_plaza.domain.exception;

public class OrderNotDoneException extends RuntimeException {
    public OrderNotDoneException() {
        super("The order is not done!");
    }
}
