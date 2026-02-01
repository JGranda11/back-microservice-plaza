package com.pragma.challenge.msvc_plaza.domain.exception;

public class OrderIsBeingPreparedException extends RuntimeException {
    public OrderIsBeingPreparedException() {
        super("Order is being prepared, cannot be canceled");
    }
}
