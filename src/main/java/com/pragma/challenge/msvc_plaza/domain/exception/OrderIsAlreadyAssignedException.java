package com.pragma.challenge.msvc_plaza.domain.exception;

public class OrderIsAlreadyAssignedException extends RuntimeException {
    public OrderIsAlreadyAssignedException() {
        super("The chosen order is assigned to another employee");
    }
}
