package com.pragma.challenge.msvc_plaza.domain.api;

import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import com.pragma.challenge.msvc_plaza.domain.util.enums.DishState;

public interface DishServicePort {
    Dish createDish(Dish dish);
    Dish modifyDish(Long id, Dish dish);
    Dish changeDishState(Long id, DishState state);
}
