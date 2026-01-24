package com.pragma.challenge.msvc_plaza.domain.spi;

import com.pragma.challenge.msvc_plaza.domain.model.Dish;

public interface DishPersistencePort {
    Dish saveDish(Dish dish);
    Dish findById(Long id);
}
