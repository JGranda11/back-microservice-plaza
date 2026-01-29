package com.pragma.challenge.msvc_plaza.domain.api;

import com.pragma.challenge.msvc_plaza.domain.model.Employee;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.DomainPage;
import com.pragma.challenge.msvc_plaza.domain.util.pagination.PaginationData;

public interface RestaurantServicePort {
    Restaurant createRestaurant(Restaurant restaurant);
    Employee registerEmployee(Employee employee);
    DomainPage<Restaurant> findPage(PaginationData paginationData);
}
