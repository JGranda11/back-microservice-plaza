package com.pragma.challenge.msvc_plaza.application.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OwnerRestaurantResponse {
    private String ownerId;
    private String restaurantId;
}
