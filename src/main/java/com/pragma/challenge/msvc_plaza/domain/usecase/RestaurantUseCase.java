package com.pragma.challenge.msvc_plaza.domain.usecase;

import com.pragma.challenge.msvc_plaza.domain.api.RestaurantServicePort;
import com.pragma.challenge.msvc_plaza.domain.exception.EntityAlreadyExistsException;
import com.pragma.challenge.msvc_plaza.domain.exception.UserRoleMustBeOwnerException;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.spi.RestaurantPersistencePort;
import com.pragma.challenge.msvc_plaza.domain.spi.UserPersistencePort;

public class RestaurantUseCase implements RestaurantServicePort {
    private final RestaurantPersistencePort restaurantPersistencePort;
    private final UserPersistencePort userPersistencePort;

    public RestaurantUseCase(RestaurantPersistencePort restaurantPersistencePort, UserPersistencePort userPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        validateRestaurant(restaurant);
        return restaurantPersistencePort.saveRestaurant(restaurant);
    }

    private void validateRestaurant(Restaurant restaurant){
        if (!userPersistencePort.isOwner(restaurant.getOwnerId())) {
            throw new UserRoleMustBeOwnerException();
        }
        if (restaurantPersistencePort.findByNit(restaurant.getNit()) != null) {
            throw new EntityAlreadyExistsException(Restaurant.class.getSimpleName(),
                    restaurant.getNit());
        }
    }
}
