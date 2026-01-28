package com.pragma.challenge.msvc_plaza.domain.api;

import com.pragma.challenge.msvc_plaza.domain.model.Employee;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;

public interface RestaurantServicePort {
    Restaurant createRestaurant(Restaurant restaurant);
    Employee registerEmployee(Employee employee);
}
