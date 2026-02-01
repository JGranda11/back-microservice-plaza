package com.pragma.challenge.msvc_plaza.domain.exception;

public class OrderDoesNotBelongToCustomerException extends RuntimeException {
    public OrderDoesNotBelongToCustomerException() {
        super("Order cannot be change because doesn't belong to the user");
    }
}
