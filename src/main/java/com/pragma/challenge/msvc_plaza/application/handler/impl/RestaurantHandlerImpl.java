package com.pragma.challenge.msvc_plaza.application.handler.impl;

import com.pragma.challenge.msvc_plaza.application.dto.request.RestaurantRequest;
import com.pragma.challenge.msvc_plaza.application.dto.response.RestaurantResponse;
import com.pragma.challenge.msvc_plaza.application.handler.RestaurantHandler;
import com.pragma.challenge.msvc_plaza.application.mapper.request.RestaurantRequestMapper;
import com.pragma.challenge.msvc_plaza.application.mapper.response.RestaurantResponseMapper;
import com.pragma.challenge.msvc_plaza.domain.api.RestaurantServicePort;
import com.pragma.challenge.msvc_plaza.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantHandlerImpl implements RestaurantHandler {
    private final RestaurantServicePort restaurantServicePort;
    private final RestaurantRequestMapper restaurantRequestMapper;
    private final RestaurantResponseMapper restaurantResponseMapper;

    @Override
    public RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRequestMapper.toDomain(restaurantRequest);
        return restaurantResponseMapper.toRespone(
                restaurantServicePort.createRestaurant(restaurant));
    }
}
