package com.pragma.challenge.msvc_plaza.domain.exception;

public class OrderIsNotInPreparationStateException extends RuntimeException {
    public OrderIsNotInPreparationStateException() {
        super("Order cannot be set as  Done because is not being prepared");
    }
}
