package com.pragma.challenge.msvc_plaza.domain.exception;

public class CustomerAlreadyHasProcessingOrderException extends RuntimeException {
    public CustomerAlreadyHasProcessingOrderException() {
        super("Customer already has a processing state");
    }

}
