package com.pragma.challenge.msvc_plaza.application.handler;

import com.pragma.challenge.msvc_plaza.application.dto.request.EmployeeRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.RestaurantRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.filter.DishFilterRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.pagination.PaginationRequest;
import com.pragma.challenge.msvc_plaza.application.dto.response.*;

public interface RestaurantHandler {
    RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest);
    EmployeeResponse registerEmployee(EmployeeRequest employeeRequest);
    PageResponse<RestaurantResponse> findPage(PaginationRequest pagination);
    PageResponse<DishResponse> findDishesOfRestaurant(Long id, PaginationRequest pagination,
                                                      DishFilterRequest filterRequest);
    OwnerRestaurantResponse findCurrentUserRestaurant();
}
