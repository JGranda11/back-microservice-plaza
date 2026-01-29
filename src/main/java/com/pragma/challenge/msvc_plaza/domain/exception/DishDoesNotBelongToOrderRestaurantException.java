package com.pragma.challenge.msvc_plaza.domain.exception;

public class DishDoesNotBelongToOrderRestaurantException extends RuntimeException {
    public DishDoesNotBelongToOrderRestaurantException(String dishName, String restaurantName) {
        super(String.format(
                "Dish called '%s' doesn't belong to Restaurant '%s'",
                dishName, restaurantName
        ));
    }
}
