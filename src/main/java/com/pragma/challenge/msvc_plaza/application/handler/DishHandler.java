package com.pragma.challenge.msvc_plaza.application.handler;

import com.pragma.challenge.msvc_plaza.application.dto.request.DishRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.DishStateRequest;
import com.pragma.challenge.msvc_plaza.application.dto.request.PatchDishRequest;
import com.pragma.challenge.msvc_plaza.application.dto.response.DishResponse;

public interface DishHandler {
    DishResponse createDish(DishRequest dishRequest);
    DishResponse modifyDish(Long id, PatchDishRequest patchDishRequest);
    DishResponse setDishState(Long id, DishStateRequest dishStateRequest);
}
