package com.pragma.challenge.msvc_plaza.domain.spi;

import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;

public interface RestaurantPersistencePort {
    Restaurant saveRestaurant(Restaurant restaurant);
    Restaurant findByNit(String nit);
}
