package com.pragma.challenge.msvc_plaza.domain.api;

import com.pragma.challenge.msvc_plaza.domain.model.Dish;

public interface DishServicePort {
    Dish createDish(Dish dish);
    Dish modifyDish(Long id, Dish dish);
}
