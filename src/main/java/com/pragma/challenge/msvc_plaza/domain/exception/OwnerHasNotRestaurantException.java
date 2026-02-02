package com.pragma.challenge.msvc_plaza.domain.exception;

public class OwnerHasNotRestaurantException extends RuntimeException {
    public OwnerHasNotRestaurantException() {
        super("Owner has not restaurant registered");
    }
}
