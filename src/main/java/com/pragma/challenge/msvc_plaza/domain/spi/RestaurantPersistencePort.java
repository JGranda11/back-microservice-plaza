package com.pragma.challenge.msvc_plaza.domain.spi;

import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.DomainPage;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.PaginationData;

public interface RestaurantPersistencePort {
    Restaurant findById(Long id);
    Restaurant saveRestaurant(Restaurant restaurant);
    Restaurant findByNit(String nit);
    DomainPage<Restaurant> findAll(PaginationData paginationData);
    Restaurant findByOwnerId(String id);
}
