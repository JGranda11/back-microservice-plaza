package com.pragma.challenge.msvc_plaza.infrastructure.input.rest;

import com.pragma.challenge.msvc_plaza.application.dto.request.RestaurantRequest;
import com.pragma.challenge.msvc_plaza.application.dto.response.RestaurantResponse;
import com.pragma.challenge.msvc_plaza.application.handler.RestaurantHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantHandler restaurantHandler;

    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(@RequestBody @Valid RestaurantRequest restaurantRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                restaurantHandler.createRestaurant(restaurantRequest)
        );
    }
}
