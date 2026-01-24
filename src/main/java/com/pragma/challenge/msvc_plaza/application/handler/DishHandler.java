package com.pragma.challenge.msvc_plaza.application.handler;

import com.pragma.challenge.msvc_plaza.application.dto.request.DishRequest;
import com.pragma.challenge.msvc_plaza.application.dto.response.DishResponse;

public interface DishHandler {
    DishResponse createDish(DishRequest dishRequest);
}
