package com.pragma.challenge.msvc_plaza.application.handler.impl;

import com.pragma.challenge.msvc_plaza.application.dto.request.DishRequest;
import com.pragma.challenge.msvc_plaza.application.dto.response.DishResponse;
import com.pragma.challenge.msvc_plaza.application.handler.DishHandler;
import com.pragma.challenge.msvc_plaza.application.mapper.request.DishRequestMapper;
import com.pragma.challenge.msvc_plaza.application.mapper.response.DishResponseMapper;
import com.pragma.challenge.msvc_plaza.domain.api.DishServicePort;
import com.pragma.challenge.msvc_plaza.domain.model.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishHanderImpl implements DishHandler {

    private final DishServicePort dishServicePort;
    private final DishRequestMapper dishRequestMapper;
    private final DishResponseMapper dishResponseMapper;
    @Override
    public DishResponse createDish(DishRequest dishRequest) {
        Dish dish = dishRequestMapper.toDomain(dishRequest);
        return dishResponseMapper.toResponse(
                dishServicePort.createDish(dish));
    }
}
