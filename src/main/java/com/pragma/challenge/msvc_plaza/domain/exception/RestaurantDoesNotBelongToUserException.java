package com.pragma.challenge.msvc_plaza.domain.exception;

public class RestaurantDoesNotBelongToUserException extends RuntimeException {
    public RestaurantDoesNotBelongToUserException() {
        super("The restaurant doesn't belong to the user");
    }
}
